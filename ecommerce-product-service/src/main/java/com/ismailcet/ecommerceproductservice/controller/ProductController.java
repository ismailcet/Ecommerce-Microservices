package com.ismailcet.ecommerceproductservice.controller;

import com.ismailcet.ecommerceproductservice.dto.request.CreateProductRequest;
import com.ismailcet.ecommerceproductservice.dto.response.ProductDto;
import com.ismailcet.ecommerceproductservice.service.ProductService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    @CachePut(value="product")
    public ResponseEntity<ProductDto> createProduct(@RequestBody @NotNull CreateProductRequest createProductRequest){
        return new ResponseEntity<>(
                productService.createProduct(createProductRequest),
                HttpStatus.CREATED
        );
    }
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProductById(@PathVariable("productId") Integer id, CreateProductRequest updateProductRequest){
        return new ResponseEntity<>(
                productService.updateProductById(id, updateProductRequest),
                HttpStatus.OK
        );
    }
    @DeleteMapping("/{productId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable("productId") Integer id){
        productService.deleteProductById(id);
    }
    @GetMapping()
    @Cacheable(value = "ProductDto")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductByProductId(@PathVariable("productId") Integer id){
        return ResponseEntity.ok(productService.getProductByProductId(id));
    }
    @GetMapping("/{productId}/stock/{quantity}")
    public ResponseEntity<String> decreaseStockByProductId(@PathVariable("productId") Integer id,@PathVariable("quantity") Integer quantity){
        return ResponseEntity.ok(productService.decreaseStockByProductId(id, quantity));
    }
}

package com.ismailcet.ecommerceproductservice.controller;

import com.ismailcet.ecommerceproductservice.dto.request.CreateProductRequest;
import com.ismailcet.ecommerceproductservice.dto.response.ProductDto;
import com.ismailcet.ecommerceproductservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/v1/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
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
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductByProductId(@PathVariable("productId") Integer id){
        return ResponseEntity.ok(productService.getProductByProductId(id));
    }
}

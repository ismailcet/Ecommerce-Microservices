package com.ismailcet.ecommerceproductservice.service;

import com.ismailcet.ecommerceproductservice.dto.converter.ProductDtoConverter;
import com.ismailcet.ecommerceproductservice.dto.request.CreateProductRequest;
import com.ismailcet.ecommerceproductservice.dto.response.ProductDto;
import com.ismailcet.ecommerceproductservice.entity.Category;
import com.ismailcet.ecommerceproductservice.entity.Product;
import com.ismailcet.ecommerceproductservice.entity.Size;
import com.ismailcet.ecommerceproductservice.exception.ProductNotFoundException;
import com.ismailcet.ecommerceproductservice.repository.CategoryRepository;
import com.ismailcet.ecommerceproductservice.repository.ProductRepository;
import com.ismailcet.ecommerceproductservice.repository.SizeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final SizeRepository sizeRepository;
    private final CategoryRepository categoryRepository;
    public ProductService(ProductRepository productRepository, SizeRepository sizeRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.sizeRepository = sizeRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductDto createProduct(CreateProductRequest createProductRequest) {
        Set<Size> sizes =
                getSizeForProduct(createProductRequest.getSizeIdList());

        Set<Category> categories =
                getCategoryForProduct(createProductRequest.getCategoryIdList());

        Product product = Product.builder()
                .name(createProductRequest.getName())
                .price(createProductRequest.getPrice())
                .stock(createProductRequest.getStock())
                .sizeProducts(sizes)
                .categoryProducts(categories)
                .build();
        productRepository.save(product);

        return ProductDtoConverter.converter(product);
    }

    public ProductDto updateProductById(Integer id, CreateProductRequest updateProductRequest) {
        Product product =
                productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Id does not exist ! "));
        Set<Size> sizes =
                getSizeForProduct(updateProductRequest.getSizeIdList());
        Set<Category> categories =
                getCategoryForProduct(updateProductRequest.getCategoryIdList());

        product.setName(updateProductRequest.getName());
        product.setPrice(updateProductRequest.getPrice());
        product.setStock(updateProductRequest.getStock());
        product.setSizeProducts(sizes);
        product.setCategoryProducts(categories);

        productRepository.save(product);

        return ProductDtoConverter.converter(product);
    }

    private Set<Category> getCategoryForProduct(List<Integer> categoryIdList) {
        return categoryIdList.stream()
                .map( c -> categoryRepository
                        .findById(c)
                        .orElseThrow(() -> new ProductNotFoundException("Category Id does not exist !")))
                .collect(Collectors.toSet());
    }

    private Set<Size> getSizeForProduct(List<Integer> sizeIdList) {
        return sizeIdList.stream()
                .map((s) -> sizeRepository
                        .findById(s)
                        .orElseThrow(
                                () -> new ProductNotFoundException("Size Id Not Found !")))
                .collect(Collectors.toSet());
    }

    public void deleteProductById(Integer id) {
        Product product =
                productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Id does not exist ! "));
        productRepository.deleteById(id);
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductDtoConverter::converter)
                .collect(Collectors.toList());
    }

    public ProductDto getProductByProductId(Integer id) {
        Product product =
                productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Id does not found ! "));

        return ProductDtoConverter.converter(product);
    }

    public String decreaseStockByProductId(Integer id, Integer number) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found ! "));

        if(product.getStock() < number){
            throw new ProductNotFoundException("Stock is Not Enough !");
        }
        product.setStock(product.getStock() - number);
        productRepository.save(product);

        return "Decrease Stock is successfully !";
    }
}

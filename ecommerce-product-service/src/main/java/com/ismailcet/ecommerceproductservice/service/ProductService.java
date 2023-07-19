package com.ismailcet.ecommerceproductservice.service;

import com.ismailcet.ecommerceproductservice.dto.request.CreateProductRequest;
import com.ismailcet.ecommerceproductservice.dto.response.ProductDto;
import com.ismailcet.ecommerceproductservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto createProduct(CreateProductRequest createProductRequest) {
        return new ProductDto();
    }
}

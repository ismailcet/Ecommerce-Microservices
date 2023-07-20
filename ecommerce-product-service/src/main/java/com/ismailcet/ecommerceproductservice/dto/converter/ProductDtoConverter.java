package com.ismailcet.ecommerceproductservice.dto.converter;

import com.ismailcet.ecommerceproductservice.dto.response.ProductDto;
import com.ismailcet.ecommerceproductservice.entity.Product;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductDtoConverter {
    public static ProductDto converter(Product product){
        return ProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .categories(product.getCategoryProducts().stream().map(CategoryDtoConverter::converter).collect(Collectors.toList()))
                .sizes(product.getSizeProducts().stream().map(SizeDtoConverter::converter).collect(Collectors.toList()))
                .build();
    }
}

package com.ismailcet.ecommerceproductservice.dto.converter;

import com.ismailcet.ecommerceproductservice.dto.response.CategoryDto;
import com.ismailcet.ecommerceproductservice.dto.response.ProductDto;
import com.ismailcet.ecommerceproductservice.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoConverter {
    public static CategoryDto converter(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}

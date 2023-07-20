package com.ismailcet.ecommerceproductservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private String name;
    private Double price;
    private Integer stock;
    private List<SizeDto> sizes;
    private List<CategoryDto> categories;
}

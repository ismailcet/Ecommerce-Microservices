package com.ismailcet.ecommerceproductservice.dto.response;

import java.util.List;

public class ProductDto {
    private String name;
    private Double price;
    private Integer stock;
    private List<SizeDto> sizes;
    private List<CategoryDto> categories;
}

package com.ismailcet.ecommerceproductservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CreateProductRequest {
    private String name;
    private Double price;
    private Integer stock;
    private List<Integer> sizeIdList;
    private List<Integer> categoryIdList;
}

package com.ismailcet.ecommerceproductservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CreateProductRequest {
    @NotNull
    private String name;
    @NotNull
    private Double price;
    @NotNull
    private Integer stock;
    @NotNull
    private List<Integer> sizeIdList;
    @NotNull
    private List<Integer> categoryIdList;
}

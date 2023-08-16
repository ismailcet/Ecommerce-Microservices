package com.ismailcet.ecommercepaymentservice.dto.response;

import lombok.Data;

@Data
public class OrderItemDto {
    private Integer productId;
    private String name;
    private Double price;
    private Integer quantity;
}

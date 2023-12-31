package com.ismailcet.ecommerceorderservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDto {
    private Integer productId;
    private String name;
    private Double price;
    private Integer quantity;
}

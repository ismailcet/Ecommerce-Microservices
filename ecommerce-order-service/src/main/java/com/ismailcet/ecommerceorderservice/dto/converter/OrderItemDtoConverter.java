package com.ismailcet.ecommerceorderservice.dto.converter;

import com.ismailcet.ecommerceorderservice.dto.response.OrderItemDto;
import com.ismailcet.ecommerceorderservice.entity.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemDtoConverter {
    public static OrderItemDto convert(OrderItem orderItem){
        return OrderItemDto.builder()
                .productId(orderItem.getProductId())
                .quantity(orderItem.getQuantity())
                .build();
    }
}

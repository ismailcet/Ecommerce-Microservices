package com.ismailcet.ecommerceorderservice.dto.converter;

import com.ismailcet.ecommerceorderservice.client.ProductServiceClient;
import com.ismailcet.ecommerceorderservice.dto.response.OrderItemDto;
import com.ismailcet.ecommerceorderservice.dto.response.ProductDto;
import com.ismailcet.ecommerceorderservice.entity.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemDtoConverter {
    private final ProductServiceClient productServiceClient;

    public OrderItemDtoConverter(ProductServiceClient productServiceClient) {
        this.productServiceClient = productServiceClient;
    }

    public OrderItemDto convert(OrderItem orderItem){
        ProductDto productDto = productServiceClient.
                getProductByProductId(orderItem.getProductId()).getBody();

        return OrderItemDto.builder()
                .productId(orderItem.getProductId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .quantity(orderItem.getQuantity())
                .build();
    }
}

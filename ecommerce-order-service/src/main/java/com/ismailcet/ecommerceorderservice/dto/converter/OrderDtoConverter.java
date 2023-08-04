package com.ismailcet.ecommerceorderservice.dto.converter;

import com.ismailcet.ecommerceorderservice.client.UserServiceClient;
import com.ismailcet.ecommerceorderservice.dto.response.OrderDto;
import com.ismailcet.ecommerceorderservice.entity.Order;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderDtoConverter {


    private final UserServiceClient userServiceClient;
    private final OrderItemDtoConverter orderItemDtoConverter;

    public OrderDtoConverter(UserServiceClient userServiceClient, OrderItemDtoConverter orderItemDtoConverter) {
        this.userServiceClient = userServiceClient;
        this.orderItemDtoConverter = orderItemDtoConverter;
    }

    public OrderDto convert(Order order){

        return OrderDto.builder()
                .id(order.getId())
                .user(userServiceClient.getUserByUserId(order.getUserId()).getBody())
                .createDate(order.getCreatedDate())
                .cargoStatus(order.getCargoStatus())
                .address(order.getAddress())
                .amount(order.getAmount())
                .orderItems(order.getOrderItems()
                        .stream()
                        .map(orderItemDtoConverter::convert)
                        .collect(Collectors.toList()))
                .build();
    }
}

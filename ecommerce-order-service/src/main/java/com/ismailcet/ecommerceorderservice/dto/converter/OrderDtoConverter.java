package com.ismailcet.ecommerceorderservice.dto.converter;

import com.ismailcet.ecommerceorderservice.dto.response.OrderDto;
import com.ismailcet.ecommerceorderservice.dto.response.UserDto;
import com.ismailcet.ecommerceorderservice.entity.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class OrderDtoConverter {

    private final RestTemplate restTemplate;

    public OrderDtoConverter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OrderDto convert(Order order){

        ResponseEntity<UserDto> responseEntity = restTemplate
                .getForEntity("http://user-service/v1/api/users/" + order.getUserId(),
                        UserDto.class);

        return OrderDto.builder()
                .id(order.getId())
                .user(responseEntity.getBody())
                .createDate(order.getCreatedDate())
                .cargoStatus(order.getCargoStatus())
                .address(order.getAddress())
                .amount(order.getAmount())
                .orderItems(order.getOrderItems()
                        .stream()
                        .map(OrderItemDtoConverter::convert)
                        .collect(Collectors.toList()))
                .build();
    }
}

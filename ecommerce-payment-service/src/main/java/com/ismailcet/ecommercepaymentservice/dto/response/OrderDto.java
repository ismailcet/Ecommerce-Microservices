package com.ismailcet.ecommercepaymentservice.dto.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private Integer id;
    private UserDto user;
    private Date createDate;
    private CargoStatus cargoStatus;
    private String address;
    private Double amount;
    private boolean paymentStatus;
    private List<OrderItemDto> orderItems;
}

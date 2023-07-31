package com.ismailcet.ecommerceorderservice.dto.response;

import com.ismailcet.ecommerceorderservice.entity.CargoStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDto {
    private Integer id;
    private UserDto user;
    private Date createDate;
    private CargoStatus cargoStatus;
    private String address;
    private Double amount;
    private List<OrderItemDto> orderItems;
}

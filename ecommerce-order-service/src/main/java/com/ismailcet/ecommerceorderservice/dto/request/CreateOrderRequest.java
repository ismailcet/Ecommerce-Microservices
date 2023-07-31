package com.ismailcet.ecommerceorderservice.dto.request;

import com.ismailcet.ecommerceorderservice.dto.response.OrderItemDto;
import com.ismailcet.ecommerceorderservice.entity.CargoStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderRequest {
     private Integer userId;
     private CargoStatus cargoStatus;
     private String address;
     private Double amount;
     private List<OrderItemDto> orderItems;
}

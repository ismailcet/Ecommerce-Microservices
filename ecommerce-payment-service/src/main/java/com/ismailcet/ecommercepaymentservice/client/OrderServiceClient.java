package com.ismailcet.ecommercepaymentservice.client;

import com.ismailcet.ecommercepaymentservice.dto.response.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service", path = "v1/order")
public interface OrderServiceClient {
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderByOrderId(@PathVariable("orderId") Integer id);
}

package com.ismailcet.ecommerceorderservice.controller;

import com.ismailcet.ecommerceorderservice.dto.request.CreateOrderRequest;
import com.ismailcet.ecommerceorderservice.dto.response.OrderDto;
import com.ismailcet.ecommerceorderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping()
    public ResponseEntity<OrderDto> createOrder(@RequestBody CreateOrderRequest createOrderRequest) throws IOException, InterruptedException {
        return new ResponseEntity<>(
                orderService.createOrder(createOrderRequest),
                HttpStatus.CREATED
        );
    }
    @GetMapping()
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderByOrderId(@PathVariable("orderId") Integer id){
        return ResponseEntity.ok(orderService.getOrderByOrderId(id));
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersByUserId(@PathVariable("userId") Integer id){
        return ResponseEntity.ok(
                orderService.getOrdersByUserId(id)
        );
    }
}

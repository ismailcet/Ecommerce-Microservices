package com.ismailcet.ecommerceorderservice.service;

import com.ismailcet.ecommerceorderservice.dto.converter.OrderDtoConverter;
import com.ismailcet.ecommerceorderservice.dto.request.CreateOrderRequest;
import com.ismailcet.ecommerceorderservice.dto.response.OrderDto;
import com.ismailcet.ecommerceorderservice.dto.response.OrderItemDto;
import com.ismailcet.ecommerceorderservice.entity.Order;
import com.ismailcet.ecommerceorderservice.entity.OrderItem;
import com.ismailcet.ecommerceorderservice.exception.OrderNotFoundException;
import com.ismailcet.ecommerceorderservice.repository.OrderRepository;
import com.ismailcet.ecommerceorderservice.utils.HttpConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDtoConverter orderDtoConverter;
    private final HttpConfig httpConfig;
    public OrderService(OrderRepository orderRepository, OrderDtoConverter orderDtoConverter, HttpConfig httpConfig) {
        this.orderRepository = orderRepository;
        this.orderDtoConverter = orderDtoConverter;
        this.httpConfig = httpConfig;
    }

    public OrderDto createOrder(CreateOrderRequest createOrderRequest) throws IOException, InterruptedException {
        Order order =
                new Order();
        userExistsSaveUserId(createOrderRequest, order);

        order.setCreatedDate(new Date());
        order.setCargoStatus(createOrderRequest.getCargoStatus());
        order.setAmount(createOrderRequest.getAmount());
        order.setAddress(createOrderRequest.getAddress());
        order.setOrderNumber(generateOrderNumber(order.getUserId()));

        order.setOrderItems(convertRequestOrderItemToOrderItemList(createOrderRequest, order));
        orderRepository.save(order);
        return orderDtoConverter.convert(order);
    }
    private List<OrderItem> convertRequestOrderItemToOrderItemList(CreateOrderRequest createOrderRequest, Order order){
        List<OrderItem> orderItems = new ArrayList<>();
        for(OrderItemDto orderItemDto:createOrderRequest.getOrderItems()){
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .quantity(orderItemDto.getQuantity())
                    .productId(orderItemDto.getProductId())
                    .build();

            orderItems.add(orderItem);
        }
        return orderItems;
    }
    private void userExistsSaveUserId(CreateOrderRequest createOrderRequest, Order order) throws IOException, InterruptedException {
        HttpResponse<String> response =
                httpConfig.createHttpRequestWithUserId("http://user-service/v1/api/users", createOrderRequest.getUserId());
        System.out.println(response.body());
        if(response.statusCode() == 200){
            order.setUserId(createOrderRequest.getUserId());
        }else{
            throw new OrderNotFoundException("User Id is not exist ! ");
        }
    }
    private String generateOrderNumber(Integer userId){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmssddMMyyyy");
        String currentDateTime = now.format(formatter);
        String randomDigits = generateNumberDigits(4);
        String combination = userId + "-" + currentDateTime + "-" + randomDigits;
        return combination;
    }
    private String generateNumberDigits(int i) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(i);

        for(int j = 0; j<i;j++){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderDtoConverter::convert)
                .collect(Collectors.toList());
    }

    public OrderDto getOrderByOrderId(Integer id){
        Order order =
                orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order Not Found ! "));

        return orderDtoConverter.convert(order);
    }

    public List<OrderDto> getOrdersByUserId(Integer id) {
        List<Order> orders = orderRepository.findAllByUserId(id);
        return orders.stream()
                .map(orderDtoConverter::convert)
                .collect(Collectors.toList());
    }
}

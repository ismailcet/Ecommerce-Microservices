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
import com.ismailcet.ecommerceorderservice.utils.OrderUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDtoConverter orderDtoConverter;
    private final OrderUtils orderUtils;
    private final RestTemplate restTemplate;
    public OrderService(OrderRepository orderRepository, OrderDtoConverter orderDtoConverter, OrderUtils orderUtils, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.orderDtoConverter = orderDtoConverter;
        this.orderUtils = orderUtils;
        this.restTemplate = restTemplate;
    }

    public OrderDto createOrder(CreateOrderRequest createOrderRequest) throws RuntimeException {
        Order order =
                new Order();

        userExistsSaveUserId(createOrderRequest, order);

        order.setCreatedDate(new Date());
        order.setCargoStatus(createOrderRequest.getCargoStatus());
        order.setAmount(createOrderRequest.getAmount());
        order.setAddress(createOrderRequest.getAddress());
        order.setOrderNumber(orderUtils.generateOrderNumber(order.getUserId()));

        order.setOrderItems(convertRequestOrderItemToOrderItemList(createOrderRequest, order));
        orderRepository.save(order);
        return orderDtoConverter.convert(order);
    }
    private List<OrderItem> convertRequestOrderItemToOrderItemList(CreateOrderRequest createOrderRequest, Order order) {
        List<OrderItem> orderItems = new ArrayList<>();
        for(OrderItemDto orderItemDto:createOrderRequest.getOrderItems()){

            OrderItem orderItem = productExistsReturnOrderItem(orderItemDto);
            orderItem.setOrder(order);

            orderItems.add(orderItem);
        }
        return orderItems;
    }
    private OrderItem productExistsReturnOrderItem(OrderItemDto orderItemDto) throws RuntimeException{
        try{
            ResponseEntity<String> response =
                    restTemplate.getForEntity("http://product-service/v1/api/products/"+orderItemDto.getProductId(),String.class);


            if(response.getStatusCodeValue() == 200){
                OrderItem orderItem = OrderItem.builder()
                        .quantity(orderItemDto.getQuantity())
                        .productId(orderItemDto.getProductId())
                        .build();
                return orderItem;
            }
            throw new OrderNotFoundException("Product Id is not found !");
        }catch (HttpStatusCodeException ex){
            throw new OrderNotFoundException("Product Id is not found ! ");
        }
    }
    private void userExistsSaveUserId(CreateOrderRequest createOrderRequest, Order order) throws RuntimeException {

        try{
            Integer response =
                    restTemplate.getForEntity("http://user-service/v1/api/users/"+createOrderRequest.getUserId(),String.class).getStatusCodeValue();
            System.out.println(response);
            if(response == 200){
                order.setUserId(createOrderRequest.getUserId());
            }
        }catch (HttpStatusCodeException ex){
            throw new OrderNotFoundException("User Id is not found ! ");
        }
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

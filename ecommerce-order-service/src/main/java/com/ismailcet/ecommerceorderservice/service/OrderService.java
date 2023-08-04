package com.ismailcet.ecommerceorderservice.service;

import com.ismailcet.ecommerceorderservice.client.ProductServiceClient;
import com.ismailcet.ecommerceorderservice.client.UserServiceClient;
import com.ismailcet.ecommerceorderservice.dto.converter.OrderDtoConverter;
import com.ismailcet.ecommerceorderservice.dto.request.CreateOrderItemRequest;
import com.ismailcet.ecommerceorderservice.dto.request.CreateOrderRequest;
import com.ismailcet.ecommerceorderservice.dto.response.OrderDto;
import com.ismailcet.ecommerceorderservice.dto.response.ProductDto;
import com.ismailcet.ecommerceorderservice.dto.response.UserDto;
import com.ismailcet.ecommerceorderservice.entity.Order;
import com.ismailcet.ecommerceorderservice.entity.OrderItem;
import com.ismailcet.ecommerceorderservice.exception.OrderNotFoundException;
import com.ismailcet.ecommerceorderservice.repository.OrderRepository;
import com.ismailcet.ecommerceorderservice.utils.OrderUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

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
    private final ProductServiceClient productServiceClient;
    private final UserServiceClient userServiceClient;
    public OrderService(OrderRepository orderRepository, OrderDtoConverter orderDtoConverter, OrderUtils orderUtils, ProductServiceClient productServiceClient, UserServiceClient userServiceClient) {
        this.orderRepository = orderRepository;
        this.orderDtoConverter = orderDtoConverter;
        this.orderUtils = orderUtils;
        this.productServiceClient = productServiceClient;
        this.userServiceClient = userServiceClient;
    }

    public OrderDto createOrder(CreateOrderRequest createOrderRequest) {
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
        for(CreateOrderItemRequest createOrderItemRequest:createOrderRequest.getOrderItems()){

            OrderItem orderItem = productExistsReturnOrderItem(createOrderItemRequest);
            orderItem.setOrder(order);

            orderItems.add(orderItem);
        }
        return orderItems;
    }
    private OrderItem productExistsReturnOrderItem(CreateOrderItemRequest createOrderItemRequest){
        try{
            ResponseEntity<ProductDto> response =
                    productServiceClient.getProductByProductId(createOrderItemRequest.getProductId());


            if(response.getStatusCodeValue() == 200){
                OrderItem orderItem = OrderItem.builder()
                        .quantity(createOrderItemRequest.getQuantity())
                        .productId(createOrderItemRequest.getProductId())
                        .build();
                return orderItem;
            }
            throw new OrderNotFoundException("Product Id is not found !");
        }catch (HttpStatusCodeException ex){
            throw new OrderNotFoundException("Product Id is not found ! ");
        }
    }
    private void userExistsSaveUserId(CreateOrderRequest createOrderRequest, Order order) {

        try{
            ResponseEntity<UserDto> response =
                    userServiceClient.getUserByUserId(createOrderRequest.getUserId());

            if(response.getStatusCode() == HttpStatus.OK){
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
        return orderRepository.findAllByUserId(id).stream()
                .map(orderDtoConverter::convert)
                .collect(Collectors.toList());
    }
}

package com.ismailcet.ecommercepaymentservice.service;

import com.ismailcet.ecommercepaymentservice.client.OrderServiceClient;
import com.ismailcet.ecommercepaymentservice.dto.request.CreatePaymentRequest;
import com.ismailcet.ecommercepaymentservice.dto.response.OrderDto;
import com.ismailcet.ecommercepaymentservice.dto.response.OrderItemDto;
import com.ismailcet.ecommercepaymentservice.dto.response.PaymentEvent;
import com.ismailcet.ecommercepaymentservice.entity.Payment;
import com.ismailcet.ecommercepaymentservice.repository.PaymentRepository;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderServiceClient orderServiceClient;
    private final NewTopic topic;
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(Payment.class);

    public PaymentService(PaymentRepository paymentRepository, OrderServiceClient orderServiceClient, NewTopic topic, KafkaTemplate<String, PaymentEvent> kafkaTemplate) {
        this.paymentRepository = paymentRepository;
        this.orderServiceClient = orderServiceClient;
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public String createPayment(CreatePaymentRequest createPaymentRequest) {
        ResponseEntity<OrderDto> order =
                orderServiceClient.getOrderByOrderId(createPaymentRequest.getOrderId());
        if(order.getStatusCodeValue() == 200){
            logger.info("Order : " + order.getBody());
            PaymentEvent paymentEvent = createPaymentEvent(order.getBody());

            Message<PaymentEvent> message = MessageBuilder.withPayload(paymentEvent)
                    .setHeader(KafkaHeaders.TOPIC, topic.name())
                    .build();

            kafkaTemplate.send(message);
        }
        logger.info(topic.name());

        return "Çalıştı ! ";
    }

    private PaymentEvent createPaymentEvent(OrderDto body) {
        Map<Integer, Integer> productList = body.getOrderItems()
                .stream()
                .collect(Collectors.
                        toMap(OrderItemDto::getProductId, OrderItemDto::getQuantity));

        return PaymentEvent.builder()
                .orderId(body.getId())
                .productIdList(productList)
                .userEmail(body.getUser().getEmail())
                .build();
    }
}

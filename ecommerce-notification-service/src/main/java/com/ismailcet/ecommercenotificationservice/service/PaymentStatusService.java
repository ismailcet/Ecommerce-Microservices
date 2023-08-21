package com.ismailcet.ecommercenotificationservice.service;

import com.ismailcet.ecommercenotificationservice.client.OrderServiceClient;
import com.ismailcet.ecommercenotificationservice.dto.UpdatePaymentStatusRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentStatusService {

    private final OrderServiceClient orderServiceClient;
    private Logger logger = LoggerFactory.getLogger(PaymentStatusService.class);

    public PaymentStatusService(OrderServiceClient orderServiceClient) {
        this.orderServiceClient = orderServiceClient;
    }

    public void changeStatusWithOrderId(Integer orderId) {
        UpdatePaymentStatusRequest status = UpdatePaymentStatusRequest.builder()
                .status(true)
                .build();

        ResponseEntity<String> response =
                orderServiceClient.changePaymentStatus(orderId, status);

        logger.info("Order Status is changed = " + orderId + " Message =" + response.getBody());
    }
}

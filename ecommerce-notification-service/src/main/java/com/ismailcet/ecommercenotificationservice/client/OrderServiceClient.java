package com.ismailcet.ecommercenotificationservice.client;

import com.ismailcet.ecommercenotificationservice.dto.UpdatePaymentStatusRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order-service",path = "v1/order")
public interface OrderServiceClient {
    @PutMapping("/status/{orderId}")
    ResponseEntity<String> changePaymentStatus(@PathVariable("orderId") Integer orderId,@RequestBody UpdatePaymentStatusRequest updatePaymentStatusRequest);
}

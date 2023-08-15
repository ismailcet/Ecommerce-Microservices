package com.ismailcet.ecommercepaymentservice.controller;

import com.ismailcet.ecommercepaymentservice.dto.request.CreatePaymentRequest;
import com.ismailcet.ecommercepaymentservice.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/order")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PostMapping()
    public ResponseEntity<String> createPayment(@RequestBody CreatePaymentRequest createPaymentRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(paymentService.createPayment(createPaymentRequest));
    }
}

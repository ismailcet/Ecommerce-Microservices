package com.ismailcet.ecommercepaymentservice.service;

import com.ismailcet.ecommercepaymentservice.dto.request.CreatePaymentRequest;
import com.ismailcet.ecommercepaymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public String createPayment(CreatePaymentRequest createPaymentRequest) {
        Order
    }
}

package com.ismailcet.ecommercepaymentservice.dto.request;

import lombok.Data;

import javax.persistence.Column;
@Data
public class CreatePaymentRequest {
    private Integer orderId;
    private String address;
    private String paymentInfo;
}

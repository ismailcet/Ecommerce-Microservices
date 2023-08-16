package com.ismailcet.ecommercepaymentservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaymentEvent {
    private String userEmail;
    private Integer orderId;
    private List<Integer> productIdList;
}

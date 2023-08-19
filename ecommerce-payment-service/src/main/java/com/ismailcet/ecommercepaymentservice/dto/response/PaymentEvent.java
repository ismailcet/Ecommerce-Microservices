package com.ismailcet.ecommercepaymentservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentEvent {
    private String userEmail;
    private Integer orderId;
    private List<Integer> productIdList;
}

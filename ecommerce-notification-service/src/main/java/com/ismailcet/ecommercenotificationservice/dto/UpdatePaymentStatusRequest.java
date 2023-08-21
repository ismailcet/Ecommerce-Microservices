package com.ismailcet.ecommercenotificationservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatePaymentStatusRequest {
    private boolean status;
}

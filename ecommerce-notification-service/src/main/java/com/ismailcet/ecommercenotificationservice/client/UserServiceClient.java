package com.ismailcet.ecommercenotificationservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-service", path="v1/user")
public interface UserServiceClient {
}

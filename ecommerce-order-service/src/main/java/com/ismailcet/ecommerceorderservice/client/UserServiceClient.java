package com.ismailcet.ecommerceorderservice.client;

import com.ismailcet.ecommerceorderservice.dto.response.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", path = "v1/user")
public interface UserServiceClient {
    @GetMapping("/{userId}")
    ResponseEntity<UserDto> getUserByUserId(@PathVariable("userId") Integer id);
}

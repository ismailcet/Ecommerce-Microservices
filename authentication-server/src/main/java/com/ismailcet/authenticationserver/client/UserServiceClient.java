package com.ismailcet.authenticationserver.client;

import com.ismailcet.authenticationserver.dto.request.CreateUserRequest;
import com.ismailcet.authenticationserver.dto.response.GetUserByUserName;
import com.ismailcet.authenticationserver.dto.response.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", path = "v1/user")
public interface UserServiceClient {
    @PostMapping()
    public ResponseEntity<UserDto> register(@RequestBody CreateUserRequest createUserRequest);
    @GetMapping("/find/{username}")
    public ResponseEntity<GetUserByUserName> getUserByUserName(@PathVariable String username);
}

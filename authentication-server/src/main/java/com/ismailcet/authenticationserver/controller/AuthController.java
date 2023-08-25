package com.ismailcet.authenticationserver.controller;

import com.ismailcet.authenticationserver.dto.request.CreateUserRequest;
import com.ismailcet.authenticationserver.dto.request.LoginRequest;
import com.ismailcet.authenticationserver.dto.response.TokenDto;
import com.ismailcet.authenticationserver.dto.response.UserDto;
import com.ismailcet.authenticationserver.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody CreateUserRequest createUserRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(createUserRequest));
    }


}

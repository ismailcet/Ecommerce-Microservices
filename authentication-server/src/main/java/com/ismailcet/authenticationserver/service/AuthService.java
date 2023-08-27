package com.ismailcet.authenticationserver.service;

import com.ismailcet.authenticationserver.client.UserServiceClient;
import com.ismailcet.authenticationserver.dto.request.CreateUserRequest;
import com.ismailcet.authenticationserver.dto.request.LoginRequest;
import com.ismailcet.authenticationserver.dto.response.GetUserByUserName;
import com.ismailcet.authenticationserver.dto.response.TokenDto;
import com.ismailcet.authenticationserver.dto.response.UserDto;
import com.ismailcet.authenticationserver.exception.WrongCredentialsException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserServiceClient userServiceClient;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, UserServiceClient userServiceClient, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userServiceClient = userServiceClient;
        this.jwtService = jwtService;
    }

    public TokenDto login(LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if(authentication.isAuthenticated()){
            GetUserByUserName user =
                    userServiceClient.getUserByUserName(request.getUsername()).getBody();
            return TokenDto.builder()
                    .token(jwtService.generateToken(request.getUsername(), user.getId()))
                    .build();
        }
        else throw new WrongCredentialsException("Wrong Credentials ! ");
    }

    public UserDto register(CreateUserRequest createUserRequest){
        return userServiceClient.register(createUserRequest).getBody();
    }
}

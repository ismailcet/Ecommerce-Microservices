package com.ismailcet.authenticationserver.service;

import com.ismailcet.authenticationserver.client.UserServiceClient;
import com.ismailcet.authenticationserver.dto.response.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserServiceClient userServiceClient;

    public CustomUserDetailService(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user =
                userServiceClient.getUserByUserName(username).getBody();
        assert user != null;
        return new CustomUserDetails(user);
    }
}

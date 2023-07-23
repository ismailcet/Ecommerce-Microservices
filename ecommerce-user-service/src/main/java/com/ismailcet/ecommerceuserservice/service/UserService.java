package com.ismailcet.ecommerceuserservice.service;

import com.ismailcet.ecommerceuserservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

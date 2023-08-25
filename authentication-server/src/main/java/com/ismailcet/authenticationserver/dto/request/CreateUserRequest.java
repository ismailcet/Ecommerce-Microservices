package com.ismailcet.authenticationserver.dto.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
    private Integer age;
    private String gender;
    private UserRole userRole = UserRole.user;
}

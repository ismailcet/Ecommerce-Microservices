package com.ismailcet.ecommerceuserservice.dto.request;

import com.ismailcet.ecommerceuserservice.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private String name;
    private String surname;
    private String email;
    private String password;
    private Integer age;
    private String gender;
    private UserRole userRole;
}

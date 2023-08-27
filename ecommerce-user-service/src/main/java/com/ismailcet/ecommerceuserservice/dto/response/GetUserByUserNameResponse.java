package com.ismailcet.ecommerceuserservice.dto.response;

import com.ismailcet.ecommerceuserservice.entity.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserByUserNameResponse {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
    private Integer age;
    private String gender;
    private UserRole userRole;
}

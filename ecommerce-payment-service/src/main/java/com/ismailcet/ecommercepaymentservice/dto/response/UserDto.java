package com.ismailcet.ecommercepaymentservice.dto.response;

import lombok.Data;

@Data
public class UserDto {
    private String name ;
    private String surname;
    private String email;
    private Integer age;
    private String gender;
    private String userRole;
}

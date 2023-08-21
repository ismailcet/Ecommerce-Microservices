package com.ismailcet.ecommercenotificationservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private String name;
    private String surname;
    private String email;
    private Integer age;
    private String gender;
}

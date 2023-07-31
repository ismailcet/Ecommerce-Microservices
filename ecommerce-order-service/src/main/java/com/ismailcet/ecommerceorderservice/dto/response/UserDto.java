package com.ismailcet.ecommerceorderservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {
    private String name ;
    private String surname;
    private String email;
    private Integer age;
    private String gender;
    private String userRole;
}

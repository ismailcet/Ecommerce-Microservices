package com.ismailcet.ecommerceuserservice.dto.response;

import com.ismailcet.ecommerceuserservice.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String name;
    private String surname;
    private String email;
    private Integer age;
    private String gender;
    private UserRole userRole;
}

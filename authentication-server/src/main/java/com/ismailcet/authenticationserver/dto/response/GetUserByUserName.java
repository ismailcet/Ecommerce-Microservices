package com.ismailcet.authenticationserver.dto.response;

import com.ismailcet.authenticationserver.dto.request.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserByUserName {
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

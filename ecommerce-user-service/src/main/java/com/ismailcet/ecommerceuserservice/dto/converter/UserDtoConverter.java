package com.ismailcet.ecommerceuserservice.dto.converter;

import com.ismailcet.ecommerceuserservice.dto.response.GetUserByUserNameResponse;
import com.ismailcet.ecommerceuserservice.dto.response.UserDto;
import com.ismailcet.ecommerceuserservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {
    public static UserDto converter(User user){
        return UserDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .age(user.getAge())
                .gender(user.getGender())
                .userRole(user.getUserRole())
                .build();
    }
    public static GetUserByUserNameResponse converterToUsername(User user){
        return GetUserByUserNameResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .age(user.getAge())
                .gender(user.getGender())
                .userRole(user.getUserRole())
                .build();
    }

}

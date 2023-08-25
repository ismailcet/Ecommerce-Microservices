package com.ismailcet.ecommerceuserservice.service;

import com.ismailcet.ecommerceuserservice.dto.converter.UserDtoConverter;
import com.ismailcet.ecommerceuserservice.dto.request.CreateUserRequest;
import com.ismailcet.ecommerceuserservice.dto.response.UserDto;
import com.ismailcet.ecommerceuserservice.entity.User;
import com.ismailcet.ecommerceuserservice.exception.UserNotFoundException;
import com.ismailcet.ecommerceuserservice.repository.UserRepository;
import com.ismailcet.ecommerceuserservice.utils.PasswordUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(CreateUserRequest createUserRequest) {
        User user = User.builder()
                .name(createUserRequest.getName())
                .surname(createUserRequest.getSurname())
                .email(createUserRequest.getEmail())
                .password(PasswordUtils.hashPassword(createUserRequest.getPassword()))
                .username(createUserRequest.getUsername())
                .age(createUserRequest.getAge())
                .gender(createUserRequest.getGender())
                .userRole(createUserRequest.getUserRole())
                .build();

        userRepository.save(user);
        return UserDtoConverter.converter(user);
    }

    public UserDto updateUserByUserId(Integer id, CreateUserRequest createUserRequest) {
        User user = userRepository.findById(id)
                        .orElseThrow(()->new UserNotFoundException("User Not Found ! "));
        user.setName(createUserRequest.getName());
        user.setSurname(createUserRequest.getSurname());
        user.setEmail(createUserRequest.getEmail());
        user.setPassword(PasswordUtils.hashPassword(createUserRequest.getPassword()));
        user.setAge(createUserRequest.getAge());
        user.setGender(createUserRequest.getGender());
        user.setUserRole(createUserRequest.getUserRole());

        userRepository.save(user);

        return UserDtoConverter.converter(user);
    }

    public UserDto getUserByUserId(Integer id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(()-> new UserNotFoundException("User Not Found"));

        return UserDtoConverter.converter(user);
    }
    public UserDto getUserByUserName(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User Not Found ! "));
        return UserDtoConverter.converter(user);
    }

    public List<UserDto> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(UserDtoConverter::converter)
                .collect(Collectors.toList());
    }

    public void deleteUserByUserId(Integer id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not Found ! "));
        userRepository.deleteById(id);
    }
}

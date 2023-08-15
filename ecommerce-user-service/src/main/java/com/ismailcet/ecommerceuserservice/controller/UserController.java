package com.ismailcet.ecommerceuserservice.controller;

import com.ismailcet.ecommerceuserservice.dto.request.CreateUserRequest;
import com.ismailcet.ecommerceuserservice.dto.response.UserDto;
import com.ismailcet.ecommerceuserservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest createUserRequest){
        return new ResponseEntity<>(
                userService.createUser(createUserRequest),
                HttpStatus.CREATED
        );
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUserByUserId(@PathVariable("userId") Integer id, @RequestBody CreateUserRequest createUserRequest){
        return ResponseEntity
                .ok(userService.updateUserByUserId(id, createUserRequest));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserByUserId(@PathVariable("userId") Integer id){
        return ResponseEntity
                .ok(userService.getUserByUserId(id));
    }
    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUserByUserId(@PathVariable("userId") Integer id){
        userService.deleteUserByUserId(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}

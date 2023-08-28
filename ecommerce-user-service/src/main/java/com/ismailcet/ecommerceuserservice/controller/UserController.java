package com.ismailcet.ecommerceuserservice.controller;

import com.ismailcet.ecommerceuserservice.dto.request.CreateUserRequest;
import com.ismailcet.ecommerceuserservice.dto.response.GetUserByUserNameResponse;
import com.ismailcet.ecommerceuserservice.dto.response.UserDto;
import com.ismailcet.ecommerceuserservice.service.UserService;
import io.jsonwebtoken.Jwt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserDto> register(@RequestBody CreateUserRequest createUserRequest){
        return new ResponseEntity<>(
                userService.createUser(createUserRequest),
                HttpStatus.CREATED
        );
    }
    @PutMapping("/{userId}")
    @PreAuthorize("authentication.principal == #id")
    public ResponseEntity<UserDto> updateUserByUserId(@PathVariable("userId") Integer id, @RequestBody CreateUserRequest createUserRequest){
        return ResponseEntity
                .ok(userService.updateUserByUserId(id, createUserRequest));
    }
    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('admin') or authentication.principal == #id")
    public ResponseEntity<UserDto> getUserByUserId(@PathVariable("userId") Integer id, Authentication authentication){
        return ResponseEntity
                .ok(userService.getUserByUserId(id));
    }
    @GetMapping("/find/{username}")
    public ResponseEntity<GetUserByUserNameResponse> getUserByUserName(@PathVariable String username){
        return ResponseEntity.ok(userService.getUserByUserName(username));
    }
    @GetMapping()
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('admin') or authentication.principal == #id")
    public ResponseEntity deleteUserByUserId(@PathVariable("userId") Integer id){
        userService.deleteUserByUserId(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}

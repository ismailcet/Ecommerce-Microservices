package com.ismailcet.authenticationserver.service;

import com.ismailcet.authenticationserver.dto.response.GetUserByUserName;
import com.ismailcet.authenticationserver.dto.response.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomUserDetails implements UserDetails {
    private final GetUserByUserName user;

    public CustomUserDetails(GetUserByUserName user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.of(user.getUserRole())
                .map(x -> new SimpleGrantedAuthority("ROLE_" + x.name()))
                .collect(Collectors.toList());
    }
    public Integer getUserId(){
        return user.getId();
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

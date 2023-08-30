package com.ismailcet.authenticationserver.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@Getter
@Builder
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ValidateException extends RuntimeException{
    private Map<String, String> validateErrors;
}

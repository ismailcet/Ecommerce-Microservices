package com.ismailcet.authenticationserver.exception;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class ValidateException extends RuntimeException{
    private Map<String, String> validateErrors;
}

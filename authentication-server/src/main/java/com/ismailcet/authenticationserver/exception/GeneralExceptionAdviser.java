package com.ismailcet.authenticationserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionAdviser extends ResponseEntityExceptionHandler {
    @ExceptionHandler(GenericErrorResponse.class)
    public ResponseEntity<?> genericError(GenericErrorResponse exception){
        Map<String, String> errors = new HashMap<>();
        errors.put("error" , exception.getMessage());
        return new ResponseEntity<>(errors, exception.getHttpStatus());
    }

    @ExceptionHandler(WrongCredentialsException.class)
    public ResponseEntity<?> wrongCredentialsException(WrongCredentialsException exception){
        Map<String, String> errors = new HashMap<>();
        errors.put("error" , exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<?> validationException(ValidateException exception){
        return ResponseEntity.badRequest().body(exception.getValidateErrors());
    }
}

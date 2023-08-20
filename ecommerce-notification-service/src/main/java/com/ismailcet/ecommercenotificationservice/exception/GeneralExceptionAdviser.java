package com.ismailcet.ecommercenotificationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionAdviser {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handle(ProductNotFoundException ex){
        return new ResponseEntity<>(ex.getExceptionMessage(),
                HttpStatus.resolve(ex.getExceptionMessage().status()));
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handle(UserNotFoundException ex){
        return new ResponseEntity<>(ex.getExceptionMessage(),
                HttpStatus.resolve(ex.getExceptionMessage().status()));
    }
}

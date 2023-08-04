package com.ismailcet.ecommerceproductservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GeneralExceptionAdviser {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handler(ProductNotFoundException exception){
        List<String> details = new ArrayList<>();
        details.add(exception.getMessage());

        ExceptionResponse response =
                new ExceptionResponse(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND,
                        exception.getMessage(),
                        details);

        return new ResponseEntity<>(response.getMessage(), response.getStatus());
    }
}

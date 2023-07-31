package com.ismailcet.ecommerceorderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GeneralExceptionAdviser {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handlerOrderNotFoundException(OrderNotFoundException ex){
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ExceptionResponse response =
                new ExceptionResponse(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND,
                        "Order Not Found ! ",
                        details);

        return new ResponseEntity<>(response, response.getStatus());
    }
}

package com.ismailcet.ecommerceuserservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionAdviser {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handlerUserNotFoundException(UserNotFoundException ex){
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ExceptionResponse response =
                new ExceptionResponse(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND,
                        ex.getMessage(),
                        details);

        return new ResponseEntity<>(response.getMessage(), response.getStatus());
    }
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> unauthorizedException(UnauthorizedException exception) {
        List<String> details = new ArrayList<>();
        details.add(exception.getMessage());

        ExceptionResponse response =
                new ExceptionResponse(
                        LocalDateTime.now(),
                        HttpStatus.UNAUTHORIZED,
                        exception.getMessage(),
                        details);

        return new ResponseEntity<>(response.getMessage(), response.getStatus());
    }
}

package com.ismailcet.authenticationserver.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Builder
@Getter
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class GenericErrorResponse extends RuntimeException{
    private final String message;
    private final HttpStatus httpStatus;

    public GenericErrorResponse(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}

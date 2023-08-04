package com.ismailcet.ecommerceorderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException{
    private ExceptionMessage exceptionMessage;
    public ProductNotFoundException(String message, ExceptionMessage exceptionMessage) {
        super(message);
        this.exceptionMessage= exceptionMessage;
    }

    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }
}

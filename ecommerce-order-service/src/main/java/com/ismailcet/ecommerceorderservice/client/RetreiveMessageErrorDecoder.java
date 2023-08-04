package com.ismailcet.ecommerceorderservice.client;

import com.ismailcet.ecommerceorderservice.exception.ExceptionMessage;
import com.ismailcet.ecommerceorderservice.exception.UserNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class RetreiveMessageErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {
        ExceptionMessage message = null;
        try(InputStream body = response.body().asInputStream()){
            message = new ExceptionMessage((String) response.headers().get("date").toArray()[0],
                    response.status(),
                    HttpStatus.resolve(response.status()).getReasonPhrase(),
                    IOUtils.toString(body, StandardCharsets.UTF_8),
                    response.request().url());
            System.out.println(response.status());
        }catch(IOException exception){
            return new Exception(exception.getMessage());
        }
        switch (response.status()){
            case 404:
                throw new UserNotFoundException(message);
            default:
                return errorDecoder.decode(s, response);
        }
    }
}

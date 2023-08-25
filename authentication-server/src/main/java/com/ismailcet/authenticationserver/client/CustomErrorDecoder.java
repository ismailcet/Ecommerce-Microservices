package com.ismailcet.authenticationserver.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ismailcet.authenticationserver.exception.GenericErrorResponse;
import com.ismailcet.authenticationserver.exception.ValidateException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class CustomErrorDecoder implements ErrorDecoder {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String s, Response response) {
        try(InputStream body = response.body().asInputStream()){
            Map<String, String> errors =
                    objectMapper.readValue(IOUtils.toString(body, String.valueOf(StandardCharsets.UTF_8)), Map.class);
            if(response.status() == 400 ){
                return ValidateException.builder()
                        .validateErrors(errors).build();
            }else{
                return GenericErrorResponse
                        .builder()
                        .httpStatus(HttpStatus.valueOf(response.status()))
                        .message(errors.get("error"))
                        .build();
            }

        } catch (IOException e) {
            throw GenericErrorResponse.builder()
                    .httpStatus(HttpStatus.valueOf(response.status()))
                    .message(e.getMessage())
                    .build();
        }
    }
}

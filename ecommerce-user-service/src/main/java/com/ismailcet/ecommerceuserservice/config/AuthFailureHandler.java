package com.ismailcet.ecommerceuserservice.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ismailcet.ecommerceuserservice.exception.ExceptionResponse;
import lombok.NoArgsConstructor;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class AuthFailureHandler {
    public Mono<Void> formatResponse(ServerHttpResponse response) {
        List<String> error = new ArrayList<>();
        response.getHeaders()
                .add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper mapper = new ObjectMapper();
        ExceptionResponse apiResponse =
                new ExceptionResponse(LocalDateTime.now(),response.getStatusCode(),"Access Denied ! ",error);

        StringBuilder json = new StringBuilder();
        try {
            json.append(mapper.writeValueAsString(apiResponse));
        } catch (JsonProcessingException jsonProcessingException) {
        }

        String responseBody = json.toString();
        byte[] bytes = responseBody.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory()
                .wrap(bytes);
        return response.writeWith(Mono.just(buffer));
    }
}

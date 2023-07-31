package com.ismailcet.ecommerceorderservice.utils;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class HttpConfig {
    public HttpResponse<String> createHttpRequestWithUserId(String uri, Integer userId) throws IOException, InterruptedException {
        HttpClient client =
                HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(uri + "/" + userId))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;
    }

}

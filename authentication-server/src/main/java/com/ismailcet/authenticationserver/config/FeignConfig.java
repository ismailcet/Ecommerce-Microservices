package com.ismailcet.authenticationserver.config;

import com.ismailcet.authenticationserver.client.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public ErrorDecoder errorDecoder(){
        return new CustomErrorDecoder();
    }
}

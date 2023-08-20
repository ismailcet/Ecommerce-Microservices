package com.ismailcet.ecommercenotificationservice;

import com.ismailcet.ecommercenotificationservice.client.RetreiveMessageErrorDecoder;
import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class EcommerceNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceNotificationServiceApplication.class, args);
	}

	@Bean
	public ErrorDecoder errorDecoder(){
		return new RetreiveMessageErrorDecoder();
	}

	@Bean
	Logger.Level feignLoggerLever(){
		return Logger.Level.FULL;
	}
}

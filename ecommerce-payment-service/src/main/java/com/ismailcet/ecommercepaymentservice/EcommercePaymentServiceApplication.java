package com.ismailcet.ecommercepaymentservice;

import com.ismailcet.ecommercepaymentservice.client.RetreiveMessageErrorDecoder;
import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class EcommercePaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommercePaymentServiceApplication.class, args);
	}

	@Bean
	public ErrorDecoder errorDecoder(){
		return new RetreiveMessageErrorDecoder();
	}

	@Bean
	Logger.Level feignLoggerLevel(){
		return Logger.Level.FULL;
	}

}

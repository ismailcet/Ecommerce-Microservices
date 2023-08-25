package com.ismailcet.authenticationserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@Slf4j
public class AuthenticationServerApplication {

	public static void main(String[] args) {
		log.info("calisti");
		SpringApplication.run(AuthenticationServerApplication.class, args);
	}

}

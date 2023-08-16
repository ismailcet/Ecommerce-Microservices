package com.ismailcet.ecommercenotificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EcommerceNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceNotificationServiceApplication.class, args);
	}

}

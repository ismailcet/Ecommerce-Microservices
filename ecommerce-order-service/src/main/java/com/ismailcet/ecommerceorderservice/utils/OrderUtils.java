package com.ismailcet.ecommerceorderservice.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
@Component
public class OrderUtils {
    public String generateOrderNumber(Integer userId){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmssddMMyyyy");
        String currentDateTime = now.format(formatter);
        String randomDigits = generateNumberDigits(4);
        String combination = userId + "-" + currentDateTime + "-" + randomDigits;
        return combination;
    }
    private String generateNumberDigits(int i) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(i);

        for(int j = 0; j<i;j++){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}

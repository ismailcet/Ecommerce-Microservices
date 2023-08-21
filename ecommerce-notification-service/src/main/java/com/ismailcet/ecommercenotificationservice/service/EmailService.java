package com.ismailcet.ecommercenotificationservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendEmailByUserEmail(String userEmail) {
        logger.info("Email was sent this User Email = "+userEmail);
    }
}

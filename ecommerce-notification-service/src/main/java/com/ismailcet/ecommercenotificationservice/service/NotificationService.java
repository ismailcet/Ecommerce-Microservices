package com.ismailcet.ecommercenotificationservice.service;


import com.ismailcet.ecommercenotificationservice.dto.PaymentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(PaymentEvent event){
        logger.info("Deneme : " + event);
    }
}

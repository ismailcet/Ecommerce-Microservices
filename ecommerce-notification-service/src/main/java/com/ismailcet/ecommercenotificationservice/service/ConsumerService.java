package com.ismailcet.ecommercenotificationservice.service;


import com.ismailcet.ecommercenotificationservice.dto.PaymentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private final Logger logger = LoggerFactory.getLogger(ConsumerService.class);
    private final StockService stockService;
    private final EmailService emailService;
    private final PaymentStatusService paymentStatusService;

    public ConsumerService(StockService stockService, EmailService emailService, PaymentStatusService paymentStatusService) {
        this.stockService = stockService;
        this.emailService = emailService;
        this.paymentStatusService = paymentStatusService;
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(PaymentEvent event){
        logger.info("Deneme : " + event);

    }
}

package com.ismailcet.ecommercepaymentservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Controller;

@Controller
public class KafkaTopicConfig {

        @Value("${spring.kafka.topic.name }")
        private String topicName ;


        // Spring bean for kafka
        @Bean
        public NewTopic topic(){
                return TopicBuilder
                        .name(topicName).build();
        }
}
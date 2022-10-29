package com.example.demo.service.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message){
        log.info(String.format("Message sent %s", message));
        kafkaTemplate.send(topicName, message);
    }
}

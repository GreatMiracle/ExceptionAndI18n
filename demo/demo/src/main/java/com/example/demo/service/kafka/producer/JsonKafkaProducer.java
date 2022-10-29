package com.example.demo.service.kafka.producer;

import com.example.demo.model.KafkaModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JsonKafkaProducer {

    @Value("${spring.kafka.topic-json.name}")
    private String topicJsonName;

    private final KafkaTemplate<String, KafkaModel> kafkaTemplateJson;

    public void sendMessage(KafkaModel data){

        log.info(String.format("Message sent -> %s", data.toString()));

        Message<KafkaModel> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, topicJsonName)
                .build();

        kafkaTemplateJson.send(message);
    }
}

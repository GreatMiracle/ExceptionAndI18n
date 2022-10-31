package com.example.demo.service.kafka.consumer;

import com.example.demo.model.KafkaModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class JsonKafkaConsumer {

    @KafkaListener(topics = "${spring.kafka.topic-json.name}"
            , groupId = "${spring.kafka.consumer.group-id}"
            , containerFactory="kafkaListenerContainerFactoryJson" //if you don't set containerFactory, program will understand default
    )
    public void consume(KafkaModel user){
        log.info(String.format("Json message recieved -> %s", user.toString()));
    }
}

package com.example.demo.service.rabbitMQ;

import com.example.demo.model.Employee;
import com.example.demo.web.rest.dto.OrderStatusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQSender {
    private final AmqpTemplate rabbitTemplateJson;
//    @Autowired
//    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    public void send1(Employee company) {
        rabbitTemplateJson.convertAndSend(exchange, routingkey, company);
//        rabbitTemplate.convertAndSend(exchange, routingkey, company);
        System.out.println("Send msg = " + company);

    }

    public void send(OrderStatusDTO company) {
        rabbitTemplateJson.convertAndSend(exchange, routingkey, company);
//        rabbitTemplate.convertAndSend(exchange, routingkey, company);
        System.out.println("Send msg = " + company);

    }
}

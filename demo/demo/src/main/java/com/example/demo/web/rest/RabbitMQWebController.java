package com.example.demo.web.rest;

import com.example.demo.model.Employee;
import com.example.demo.service.rabbitMQ.RabbitMQSender;
import com.example.demo.web.rest.dto.OrderDTO;
import com.example.demo.web.rest.dto.OrderStatusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/rabbitmq")
@RequiredArgsConstructor
public class RabbitMQWebController {

    @Value("${rabbitmq.exchange}")
    String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    private final RabbitMQSender rabbitMQSender;

//    @GetMapping(value = "/producer")
    @PostMapping(value = "/producer")
//    public String producer(@RequestParam("empName") String empName, @RequestParam("empId") String empId) {
    public String producer(@RequestBody Employee e) {

        Employee emp=new Employee();
        emp.setEmpId(e.getEmpId());
        emp.setEmpName(e.getEmpId());
        rabbitMQSender.send1(emp);

        return "Message sent to the RabbitMQ Successfully";
    }

//    @PostMapping("/send")
//    public void sendMessage(@RequestBody Employee messageDto) {
//        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, messageDto.getEmpName()+ " - "+messageDto.getEmpId());
//    }

//    @Autowired
//    private RabbitTemplate rabbitTemplateJson;
//
//    @PostMapping("/{restaurantName}")
//    public String bookOrder(@RequestBody OrderDTO order, @PathVariable String restaurantName) {
//        order.setOrderId(UUID.randomUUID().toString());
//        //restaurantservice
//        //payment service
//        OrderStatusDTO orderStatus = new OrderStatusDTO(order, "PROCESS", "order placed succesfully in " + restaurantName);
////        rabbitMQSender.send(orderStatus);
//        rabbitTemplateJson.convertAndSend(exchange, routingkey, orderStatus);
//        return "Success !!";
//    }


//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @PostMapping("/send")
//    public void sendMessage(@RequestBody OrderDTO order) {
//        order.setOrderId(UUID.randomUUID().toString());
//        OrderStatusDTO orderStatus = new OrderStatusDTO(order, "PROCESS", "order placed succesfully in ");
//        rabbitTemplate.convertAndSend(exchange, routingkey, orderStatus);
//    }
}

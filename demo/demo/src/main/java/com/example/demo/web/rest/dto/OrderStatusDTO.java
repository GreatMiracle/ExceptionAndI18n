package com.example.demo.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderStatusDTO {

    private OrderDTO order;
    private String status;//progress,completed
    private String message;
}
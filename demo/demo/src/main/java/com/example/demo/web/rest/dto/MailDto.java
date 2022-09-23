package com.example.demo.web.rest.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailDto {

    private List<String> emailTo;
    private String subject;
    private String template;
//    private Map<String, Object> model;
}

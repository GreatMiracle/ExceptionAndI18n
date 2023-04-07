package com.example.demo.web.rest.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailAttachDto {

    private MailDto mailDto;
    private IncredibleVoiceTemplateDTO incredibleVoiceTemplateDTO;

}

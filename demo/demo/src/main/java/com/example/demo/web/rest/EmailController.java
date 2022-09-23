package com.example.demo.web.rest;

import com.example.demo.config.enums.ApiResponseCode;
import com.example.demo.service.impl.EmailSenderService;
import com.example.demo.util.exception.RestException;
import com.example.demo.web.rest.dto.MailDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;

@Controller
@AllArgsConstructor
public class EmailController {
    private final EmailSenderService emailSenderService;

    // Sending email with attachment
    @PostMapping("/sendMail")
    public String sendMailWithAttachment(
            @RequestBody MailDto details) {
        String status;
        try {
            status = emailSenderService.sendEmail(details);
        } catch ( MessagingException e){
            throw  new RestException(ApiResponseCode.TEST_I18N);
        }
        return status;
    }
}
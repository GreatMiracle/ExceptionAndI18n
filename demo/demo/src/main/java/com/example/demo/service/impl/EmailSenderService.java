package com.example.demo.service.impl;

import com.example.demo.service.ReportService;
import com.example.demo.web.rest.dto.MailAttachDto;
import com.example.demo.web.rest.dto.MailDto;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    @Value("${spring.mail.username}")
    private String emailFrom;
    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;

    private final ReportService reportService;

    public String sendEmail(MailDto dto) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
//        Context context = new Context();
////        context.setVariables(dto.getModel());
//        context.setVariables(null);
//        String html = templateEngine.process(dto.getTemplate(), context);
        helper.setFrom(emailFrom);
        helper.setTo(dto.getEmailTo().toArray(new String[0]));
//        helper.setText(html, true);
        helper.setText(dto.getTemplate());
        helper.setSubject(dto.getSubject());
        emailSender.send(message);
        return "Mail sent Successfully";
    }


    public String sendEmailAttach(MailAttachDto dto) throws MessagingException {
        byte[] byteArr = new byte[0];
        try {
            byteArr = reportService.exportIncredibleVoice(dto.getIncredibleVoiceTemplateDTO());
        } catch (JRException e) {
            e.printStackTrace();
        }
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
//        Context context = new Context();
////        context.setVariables(dto.getModel());
//        context.setVariables(null);
//        String html = templateEngine.process(dto.getTemplate(), context);
        helper.setFrom(emailFrom);
        helper.setTo(dto.getMailDto().getEmailTo().toArray(new String[0]));
//        helper.setText(html, true);
        helper.setText(dto.getMailDto().getTemplate());
        helper.setSubject(dto.getMailDto().getSubject());
        // Attach the PDF file to the email
        ByteArrayResource pdf = new ByteArrayResource(byteArr);
        helper.addAttachment("LeTheSieu_fileTestSendMailPdf.pdf", pdf);
        emailSender.send(message);
        return "Mail sent Successfully";
    }
    //tạo mật khẩu ứng dụng trên google cho vào pass mới chạy được
}

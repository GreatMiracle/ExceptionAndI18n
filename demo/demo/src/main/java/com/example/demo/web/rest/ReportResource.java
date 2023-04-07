package com.example.demo.web.rest;

import com.example.demo.service.ReportService;
import com.example.demo.web.rest.dto.IncredibleVoiceTemplateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/report")
public class ReportResource {
    private final ReportService reportService;

    @PostMapping("/export-MB14-phieu-danh-gia-tang-cuong")
    public ResponseEntity<byte[]> exportIncredibleVoice(@RequestBody IncredibleVoiceTemplateDTO reqDTO) throws JRException {
        byte[] byteArr = reportService.exportIncredibleVoice(reqDTO);
        HttpHeaders headers = new HttpHeaders();
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment").filename("MB14-Phieu-Danh-Gia-Tang-Cuong.pdf").build();
        headers.setContentDisposition(contentDisposition);
        MediaType mediaType = MediaType.parseMediaType("application/pdf;charset=utf-8");
        headers.setContentType(mediaType);
        return ResponseEntity.ok().headers(headers).body(byteArr);
    }
}

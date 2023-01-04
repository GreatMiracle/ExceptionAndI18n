package com.example.demo.service;

import com.example.demo.web.rest.dto.IncredibleVoiceTemplateDTO;
import net.sf.jasperreports.engine.JRException;

public interface ReportService {
    byte[] exportIncredibleVoice(IncredibleVoiceTemplateDTO item) throws JRException;

}

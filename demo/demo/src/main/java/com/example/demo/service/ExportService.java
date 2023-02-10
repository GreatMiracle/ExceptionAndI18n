package com.example.demo.service;

import com.example.demo.web.rest.dto.DailyLimitClosingReportReq;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public interface ExportService {
    <T> String export(String fileName, List<T> data, Class<T> convertClass);

    <T> String exportPersonalized(String fileName, List<T> data, Class<T> convertClass, String[] columnName);

    ByteArrayOutputStream exportDailyLimitClosingReportExcel(DailyLimitClosingReportReq req) throws IOException;
}

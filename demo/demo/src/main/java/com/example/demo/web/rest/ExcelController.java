package com.example.demo.web.rest;

import com.example.demo.model.User;
import com.example.demo.service.ExportService;
import com.example.demo.service.UserServices;
import com.example.demo.util.*;
import com.example.demo.web.rest.dto.DailyLimitClosingReportReq;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ExcelController {
    private final MessageUtils messageUtils;
    private final UserServices userServices;
    private final ExportService exportService;

//    @GetMapping("/export/excel")
//    public void exportToExcel(HttpServletResponse response) throws IOException {
//        setNameFile(response);
//        List<User> listUsers = userServices.listAll();
//        UserExcelExporter excelExporter = new UserExcelExporter(listUsers);
//
//        excelExporter.export(response);
//    }
//
//    private static void setNameFile(HttpServletResponse response) {
//        response.setContentType("application/octet-stream");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
//        response.setHeader(headerKey, headerValue);
//    }

    @Value("${export.dir}")
    private String exportFileDir;

    @GetMapping("/export/excelKien")
    public void getSuccessOProgram(HttpServletResponse response) throws IOException {
        String filename = userServices.listAllKien();
        File file = new File(exportFileDir.concat(filename));
        try(InputStream inputStream = new FileInputStream(file)){
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        }

    }

    @PostMapping("/export-daily-limit-closing-report-excel") //báo cáo đóng hạn mức theo ngày, bc thẻ đóng theo ngày, hạn mức cần đóng
    public ResponseEntity<byte[]> exportDailyLimitClosingReportExcel(@RequestBody DailyLimitClosingReportReq reqDTO) throws IOException {
        ByteArrayOutputStream outputStream = exportService.exportDailyLimitClosingReportExcel(reqDTO);
        String fileNameExcel = reqDTO.getTitle();
        byte[] byteArr = outputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment").filename(fileNameExcel +".xlsx").build();
        headers.setContentDisposition(contentDisposition);
        MediaType mediaType = MediaType.parseMediaType("application/octet-stream");
        headers.setContentType(mediaType);
        return ResponseEntity.ok().headers(headers).body(byteArr);
    }

}

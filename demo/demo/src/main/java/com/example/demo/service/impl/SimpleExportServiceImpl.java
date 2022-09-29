//package com.example.demo.service.impl;
//
//import com.example.demo.service.ExportService;
//import com.example.demo.util.ExcelConfig;
//import com.example.demo.util.annotation.DatePattern;
//import com.example.demo.util.annotation.ExcelColumn;
//import com.example.demo.util.annotation.ExcelEntity;
//import com.example.demo.util.exception.RequireException;
//import lombok.RequiredArgsConstructor;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.time.DateFormatUtils;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.dhatim.fastexcel.BorderStyle;
//import org.dhatim.fastexcel.Workbook;
//import org.dhatim.fastexcel.Worksheet;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import vn.com.vinare.excelservice.excel.annotation.*;
//import vn.com.vinare.excelservice.excel.config.ExcelConfig;
//import vn.com.vinare.excelservice.service.ExportService;
//import vn.com.vinare.excelservice.web.rest.errors.RequireException;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.lang.reflect.Field;
//import java.math.BigDecimal;
//import java.text.DecimalFormat;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class SimpleExportServiceImpl implements ExportService {
////    @Value("${export.dir}")
////    private String exportFileDir;
////
////    @Value("${export.prefix_download}")
////    private String prefixDownload;
////
////    @SneakyThrows
////    @Override
////    public <T> String export(String fileName, List<T> data, Class<T> mappingClass) {
////        ExcelEntity excelEntity = mappingClass.getAnnotation(ExcelEntity.class);
////        if (excelEntity == null) {
////            throw new RequireException("Require annotation @ExcelEntity !");
////        }
////        String filePath = exportFileDir.concat(fileName);
////        File file = new File(exportFileDir);
////        if (!file.exists()) {
////            file.mkdirs();
////        }
////        try (OutputStream os = new FileOutputStream(filePath)) {
////            XSSFWorkbook wb = new XSSFWorkbook();
////            XSSFSheet ws= wb.createSheet(excelEntity.sheetName());
////            createHeader(ws, mappingClass);
////            createBody(ws, data, mappingClass);
////            wb.close();
////        }
////        return prefixDownload.concat(fileName);
////    }
////
////    @SneakyThrows
////    @Override
////    public <T> String exportPersonalized(String fileName, List<T> data, Class<T> mappingClass, String[] columnName) {
////        ExcelEntity excelEntity = mappingClass.getAnnotation(ExcelEntity.class);
////        if (excelEntity == null) {
////            throw new RequireException("Require annotation @ExcelEntity !");
////        }
////        if(columnName == null || columnName.length == 0){
////            throw new RequireException("Require columns export");
////        }
////        String filePath = exportFileDir.concat(fileName);
////        File file = new File(exportFileDir);
////        if (!file.exists()) {
////            file.mkdirs();
////        }
////        long startTime = System.nanoTime();
////        log.error("===========START_OutputStream==============={}" , startTime);
////        try (OutputStream os = new FileOutputStream(filePath)) {
////            XSSFWorkbook wb = new XSSFWorkbook();
////            XSSFSheet ws= wb.createSheet(excelEntity.sheetName());
////            createHeader(ws, mappingClass, columnName);
////            createBodyPersonalized(ws, data, mappingClass, columnName);
////            wb.close();
////        }
////        log.error("===========END_OutputStream==============={}" , System.nanoTime() - startTime);
////        return prefixDownload.concat(fileName);
////    }
////
////    protected <T> void createHeader(XSSFSheet ws, Class<T> mappingClass) {
////        createHeader(ws, mappingClass, ExcelConfig.DEFAULT_HEADER_FILL_COLOR, ExcelConfig.DEFAULT_HEADER_FONT_COLOR);
////    }
////
////    protected <T> void createHeader(XSSFSheet ws, Class<T> mappingClass, String[] columnName) {
////        createHeaderPersonalized(ws, mappingClass, ExcelConfig.EXPORT_HEADER_FILL_COLOR, ExcelConfig.EXPORT_HEADER_FONT_COLOR, columnName);
////    }
////
////    protected <T> void createHeader(XSSFSheet ws, Class<T> mappingClass, String fillColor, String fontColor) {
////        Field[] fields = mappingClass.getDeclaredFields();
////        for (Field field : fields) {
////            ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
////            if (excelColumn == null) {
////                continue;
////            }
////            ws.setColumnWidth(excelColumn.index(), excelColumn.size());
////            ws.style(0, excelColumn.index()).fillColor(fillColor).fontColor(fontColor).borderStyle(BorderStyle.THIN).bold()
////                .borderColor(ExcelConfig.DEFAULT_BODY_FONT_COLOR).set();
////            ws.value(0, excelColumn.index(), excelColumn.name());
////        }
////    }
////
////    @SneakyThrows
////    protected <T> void createBody(XSSFSheet ws, List<T> data, Class<T> mappingClass) {
////        int index = 1;
////        for (T t : data) {
////            Field[] fields = mappingClass.getDeclaredFields();
////            for (Field field : fields) {
////                boolean accessible = field.trySetAccessible();
////                field.setAccessible(true);
////                ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
////                if (excelColumn == null) {
////                    continue;
////                }
////                ws.style(index, excelColumn.index()).fontColor(ExcelConfig.DEFAULT_BODY_FONT_COLOR)
//////                    .horizontalAlignment("fill")
////                    .borderStyle(BorderStyle.THIN).borderColor(ExcelConfig.DEFAULT_BODY_FONT_COLOR).set();
////                if(field.get(t) == null){
////                    continue;
////                }
////                Class<?> fieldType = field.getType();
////                if (Number.class.isAssignableFrom(fieldType)) {
////                    ws.value(index, excelColumn.index(), String.valueOf(field.get(t)));
////                } else if (fieldType.equals(String.class)) {
////                    ws.value(index, excelColumn.index(), (String) field.get(t));
////                } else if (fieldType.equals(Boolean.class)) {
////                    ws.value(index, excelColumn.index(), getBooleanValue(field, t));
////                } else if (fieldType.equals(Date.class)) {
////                    DatePattern datePattern = field.getAnnotation(DatePattern.class);
////                    Date val = (Date) field.get(t);
////                    if (Objects.nonNull(val)) {
////                        ws.value(index, excelColumn.index(), DateFormatUtils.format(val, datePattern.pattern()));
////                    }
////                } else if (fieldType.equals(LocalDate.class)) {
////                    DatePattern datePattern = field.getAnnotation(DatePattern.class);
////                    LocalDate val = (LocalDate) field.get(t);
////                    if (Objects.nonNull(val)) {
////                        ws.value(index, excelColumn.index(), val.format(DateTimeFormatter.ofPattern(datePattern.pattern())));
////                    }
////                } else if (fieldType.equals(LocalDateTime.class)) {
////                    DatePattern datePattern = field.getAnnotation(DatePattern.class);
////                    LocalDateTime val = (LocalDateTime) field.get(t);
////                    // If value is null, not fill to excel
////                    if (Objects.nonNull(val)) {
////                        ws.value(index, excelColumn.index(), val.format(DateTimeFormatter.ofPattern(datePattern.pattern())));
////                    }
////                } else {
////                    throw new UnsupportedOperationException("UnSupport field type !");
////                }
////                field.setAccessible(accessible);
////            }
////            ++index;
////        }
////    }
////
////    @SneakyThrows
////    protected <T> void createBodyPersonalized(Worksheet ws, List<T> data, Class<T> mappingClass, String[] columnName) {
////        int index = 1;
////        for (T t : data) {
////            int i = 0;
////            for(String column:columnName){
////                Field[] fields = mappingClass.getDeclaredFields();
////                for (Field field : fields) {
////                    boolean accessible = field.trySetAccessible();
////                    field.setAccessible(true);
////                    ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
////                    if (excelColumn == null) {
////                        continue;
////                    }
////                    if(field.getName().equals(column)){
////                        ws.style(index, i).fontColor(ExcelConfig.DEFAULT_BODY_FONT_COLOR)
////                            .borderStyle(BorderStyle.THIN).borderColor(ExcelConfig.DEFAULT_BODY_FONT_COLOR).set();
////                        ws.style(index, i).horizontalAlignment(excelColumn.align()).wrapText(true).verticalAlignment("center").set();
////                        if(field.get(t) == null){
////                            continue;
////                        }
////                        Class<?> fieldType = field.getType();
////                        if (Number.class.isAssignableFrom(fieldType)) {
////                            BigDecimalFormat bigDecimalFormat = field.getAnnotation(BigDecimalFormat.class);
////                            if (bigDecimalFormat != null) {
////                                if(bigDecimalFormat.trailingZeros()){
////                                    BigDecimal val = (BigDecimal) field.get(t);
////                                    if (Objects.nonNull(val)) {
////                                        ws.value(index, i, val.stripTrailingZeros());
////                                    }
////                                }else{
////                                    DecimalFormat df = new DecimalFormat("#,##0.00");
////                                    BigDecimal val = (BigDecimal) field.get(t);
////                                    if (Objects.nonNull(val)) {
////                                        ws.value(index, i, df.format(val));
////                                    }
////                                }
////                            } else{
////                                ws.value(index, i, String.valueOf(field.get(t)));
////                            }
////                        } else if (fieldType.equals(String.class)) {
////                            ws.value(index, i, (String) field.get(t));
////                        } else if (fieldType.equals(Boolean.class)) {
////                            ws.value(index, i, getBooleanValue(field, t));
////                        } else if (fieldType.equals(Date.class)) {
////                            DatePattern datePattern = field.getAnnotation(DatePattern.class);
////                            Date val = (Date) field.get(t);
////                            if (Objects.nonNull(val)) {
////                                ws.value(index, i, DateFormatUtils.format(val, datePattern.pattern()));
////                            }
////                        } else if (fieldType.equals(LocalDate.class)) {
////                            DatePattern datePattern = field.getAnnotation(DatePattern.class);
////                            LocalDate val = (LocalDate) field.get(t);
////                            if (Objects.nonNull(val)) {
////                                ws.value(index, i, val.format(DateTimeFormatter.ofPattern(datePattern.pattern())));
////                            }
////                        } else if (fieldType.equals(LocalDateTime.class)) {
////                            DatePattern datePattern = field.getAnnotation(DatePattern.class);
////                            LocalDateTime val = (LocalDateTime) field.get(t);
////                            // If value is null, not fill to excel
////                            if (Objects.nonNull(val)) {
////                                ws.value(index, i, val.format(DateTimeFormatter.ofPattern(datePattern.pattern())));
////                            }
////                        } else {
////                            throw new UnsupportedOperationException("UnSupport field type !");
////                        }
////                        field.setAccessible(accessible);
////                        break;
////                    }
////                }
////                i++;
////            }
////            ++index;
////        }
////    }
////
////    protected <T> void createHeaderPersonalized(Worksheet ws, Class<T> mappingClass, String fillColor, String fontColor, String[] columnName) {
////        Field[] fields = mappingClass.getDeclaredFields();
////        int i = 0;
////        for(String column:columnName){
////            for (Field field : fields) {
////                ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
////                if (excelColumn == null) {
////                    continue;
////                }
////                if(field.getName().equals(column)){
////                    ws.width(i, excelColumn.size());
////                    ws.style(0, i).fillColor(fillColor).fontColor(fontColor).borderStyle(BorderStyle.THIN)
//////                        .borderColor(ExcelConfig.CLAIM_HEADER_FONT_COLOR)
////                        .bold().wrapText(true).verticalAlignment("center").set();
////                    ws.value(0, i, excelColumn.name());
////                    break;
////                }
////            }
////            i++;
////        }
////    }
////
////    @SneakyThrows
////    protected String getBooleanValue(Field field, Object t) {
////        BooleanValue booleanValue = field.getAnnotation(BooleanValue.class);
////        boolean val = (Boolean) field.get(t);
////        String strValue;
////        if (val) {
////            strValue = booleanValue.trueValue();
////        } else {
////            strValue = booleanValue.falseValue();
////        }
////        return strValue;
////    }
//
//}

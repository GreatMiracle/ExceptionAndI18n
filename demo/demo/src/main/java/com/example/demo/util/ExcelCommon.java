//package com.example.demo.util;
//
//import com.example.demo.model.User;
//import org.apache.poi.ss.formula.functions.T;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//public class ExcelCommon {
//    private XSSFWorkbook workbook;
//    private XSSFSheet sheet;
////    private static void setNameFile(HttpServletResponse response) {
////        response.setContentType("application/octet-stream");
////        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
////        String currentDateTime = dateFormatter.format(new Date());
////
////        String headerKey = "Content-Disposition";
////        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
////        response.setHeader(headerKey, headerValue);
////    }
//
//    private void writeHeaderLine(String nameSheet, List<String> nameColumn) {
//        sheet = workbook.createSheet(nameSheet);
//
//        Row row = sheet.createRow(0);
//
//        CellStyle style = workbook.createCellStyle();
//        XSSFFont font = workbook.createFont();
//        font.setBold(true);
//        font.setFontHeight(16);
//        style.setFont(font);
//
//        for (int i = 0; i< nameColumn.size(); i++){
//            createCell(row, i, nameColumn.get(i), style);
//        }
//    }
//
//    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
//        sheet.autoSizeColumn(columnCount);
//        Cell cell = row.createCell(columnCount);
//        if (value instanceof Integer) {
//            cell.setCellValue((Integer) value);
//        } else if (value instanceof Boolean) {
//            cell.setCellValue((Boolean) value);
//        }else {
//            cell.setCellValue((String) value);
//        }
//        cell.setCellStyle(style);
//    }
//
//    private void writeDataLines(List<T> listData, Class<T> convertClass) {
//        int rowCount = 1;
//
//        CellStyle style = workbook.createCellStyle();
//        XSSFFont font = workbook.createFont();
//        font.setFontHeight(14);
//        style.setFont(font);
//
//        for (T user : listData) {
//            Row row = sheet.createRow(rowCount++);
//            int columnCount = 0;
//
//            createCell(row, columnCount++, user.g(), style);
//            createCell(row, columnCount++, user.getEmail(), style);
//            createCell(row, columnCount++, user.getFullName(), style);
//            createCell(row, columnCount++, user.getRoles().toString(), style);
//            createCell(row, columnCount++, user.isEnabled(), style);
//
//        }
//    }
//}

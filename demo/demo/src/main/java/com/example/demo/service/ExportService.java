package com.example.demo.service;

import java.util.List;

public interface ExportService {
    <T> String export(String fileName, List<T> data, Class<T> convertClass);

    <T> String exportPersonalized(String fileName, List<T> data, Class<T> convertClass, String[] columnName);
}

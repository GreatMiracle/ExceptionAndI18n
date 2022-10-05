package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface AwsService {

    public String uploadFile(MultipartFile file);

    public byte[] downloadFile(String fileName);

    public String deleteFile(String fileName);

}

package com.example.demo.service;

import io.minio.messages.DeleteObject;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface MinIOService {

    InputStream downloadFile(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException;

    String uploadFile(MultipartFile file);

    String uploadFile(MultipartFile file, String fileName);

    String getFilePreSignedUrl(String fileName);

    void deleteFiles(List<DeleteObject> objects);
}

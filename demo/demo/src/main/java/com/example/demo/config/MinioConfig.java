package com.example.demo.config;

import io.minio.MinioClient;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MinioConfig {

    @Value("${spring.minio.accessKey}")
    private String accessKey;
    @Value("${spring.minio.secretKey}")
    private String accessSecret;
    @Value("${spring.minio.endpoint.client}")
    private String urlClient;

    @Bean(name = "minioClient")
    public MinioClient generateClientSite() throws MinioException {
        return MinioClient.builder()
                .endpoint(urlClient)
                .credentials(accessKey, accessSecret)
                .build();
    }
}
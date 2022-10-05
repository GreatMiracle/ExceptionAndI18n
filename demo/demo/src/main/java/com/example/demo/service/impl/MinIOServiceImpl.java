package com.example.demo.service.impl;

import com.example.demo.service.MinIOService;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectsArgs;
import io.minio.Result;
import io.minio.errors.MinioException;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MinIOServiceImpl implements MinIOService {

	private final MinioClient minioClient;

	@Value("${spring.minio.bucket-name}")
	private String bucketName;

	@Value("${spring.minio.file-size}")
	private Integer fileSize;

	@Value("${spring.minio.object-size}")
	private Integer objectSize;

	@Value("${domain.download}")
	private String domain;

	@Override
	public InputStream downloadFile(String fileName)
			throws InvalidKeyException, IllegalArgumentException, NoSuchAlgorithmException, IOException {
		try {
			boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
			if (!bucketExists) {
				minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
			}
			return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build());
		} catch (MinioException e) {
			log.info("Error occurred: {}" + e.getMessage());
			return null;
		}
	}

	@Override
	@SneakyThrows
	public String uploadFile(MultipartFile file) {
		String originalFilename = file.getOriginalFilename();
		PutObjectArgs putObjectArgs = PutObjectArgs.builder().bucket(bucketName).object(originalFilename)
				.stream(file.getInputStream(), objectSize, fileSize).contentType(file.getContentType()).build();

		minioClient.putObject(putObjectArgs);
		return domain + originalFilename;
	}

	@Override
	@SneakyThrows
	public String uploadFile(MultipartFile file, String fileName) {
		PutObjectArgs putObjectArgs = PutObjectArgs.builder().bucket(bucketName).object(fileName)
				.stream(file.getInputStream(), objectSize, fileSize).contentType(file.getContentType()).build();

		minioClient.putObject(putObjectArgs);
		return domain + fileName;
	}

	@Override
	@SneakyThrows
	public String getFilePreSignedUrl(String fileName) {
		return domain + fileName;
	}

	@Override
	public void deleteFiles(List<DeleteObject> objects) {

		Iterable<Result<DeleteError>> results = minioClient
				.removeObjects(RemoveObjectsArgs.builder().bucket(bucketName).objects(objects).build());
		try {
			for (Result<DeleteError> result : results) {
				DeleteError error = result.get();
				log.debug("DeleteError: {}", error);
			}
		} catch (Exception e) {
			log.debug("", e);
		}
	}
}

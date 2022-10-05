package com.example.demo.web.rest;

import com.example.demo.config.enums.ApiResponseCode;
import com.example.demo.model.User;
import com.example.demo.respository.UserRepository;
import com.example.demo.service.TestConnectMinioService;
import com.example.demo.util.BodyResponseDTO;
import com.example.demo.util.MessageUtils;
import com.example.demo.util.RestResponseWrapper;
import com.example.demo.util.exception.RestException;
import com.example.demo.web.rest.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/minio")
public class ConnectMinioController {

    private final MessageUtils messageUtils;
    private final TestConnectMinioService testConnectMinioService;

    @PatchMapping(value = "/upload-image", consumes = "multipart/form-data")
    public ResponseEntity<BodyResponseDTO<Object>> updateProfile(@ModelAttribute @Valid UserDTO dto) {
        try {
            testConnectMinioService.updateGeneralConfig(dto);
        } catch (IOException e) {
            throw new RestException(ApiResponseCode.BAD_REQUEST);
        }
        return RestResponseWrapper.getSuccess(messageUtils);
    }

}

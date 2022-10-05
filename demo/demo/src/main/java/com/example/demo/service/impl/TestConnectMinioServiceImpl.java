package com.example.demo.service.impl;

import com.example.demo.model.constant.Constant;
import com.example.demo.service.MinIOService;
import com.example.demo.service.TestConnectMinioService;
import com.example.demo.web.rest.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.compress.utils.FileNameUtils;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TestConnectMinioServiceImpl implements TestConnectMinioService {
    private final MinIOService minIOService;
    @Override
    public void updateGeneralConfig(UserDTO dto) throws IOException {
        MultipartFile logo = dto.getAvatar();
        if (Objects.nonNull(logo)) {

            String logoPic = Constant.LOGO_POSTFIX + FileNameUtils.getExtension(logo.getOriginalFilename());
            minIOService.uploadFile(logo, logoPic);
        }
    }
}

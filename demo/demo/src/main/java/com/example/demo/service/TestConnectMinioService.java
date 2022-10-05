package com.example.demo.service;

import com.example.demo.web.rest.dto.UserDTO;

import java.io.IOException;

public interface TestConnectMinioService {
    void updateGeneralConfig(UserDTO dto) throws IOException;
}

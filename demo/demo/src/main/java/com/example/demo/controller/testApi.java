package com.example.demo.controller;

import com.example.demo.service.testApiService;
import com.example.demo.util.BodyResponseDTO;
import com.example.demo.util.MessageUtils;
import com.example.demo.util.RestResponseWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequiredArgsConstructor
public class testApi {

    private final MessageUtils messageUtils;
    private final testApiService testApiService;

    public testApi(MessageUtils messageUtils, com.example.demo.service.testApiService testApiService) {
        this.messageUtils = messageUtils;
        this.testApiService = testApiService;
    }

    @GetMapping("/")
    public ResponseEntity<BodyResponseDTO<Object>> getAllABC() {
        return RestResponseWrapper.getSuccess( testApiService.testException());
    }
}

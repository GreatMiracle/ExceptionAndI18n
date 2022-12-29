package com.example.demo.service.impl;

import com.example.demo.config.enums.ApiResponseCode;
import com.example.demo.model.constant.Constant;
import com.example.demo.util.JsonF;
import com.example.demo.util.exception.RestException;
import com.example.demo.model.Student;
import com.example.demo.service.testApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class testApiServiceImpl implements testApiService {
    @Override
    public Student testException()  {
        Student abc =  Student.builder()
                .name(Constant.NAME_USER)
                .age(Constant.AGE_USER)
                .build();
        String json = JsonF.toString(abc);
        log.debug("test log object <-------- {} ------>",json);
        if (1 != 1){
            throw new RestException(ApiResponseCode.TEST_I18N);
        }
        return abc;
    }
}

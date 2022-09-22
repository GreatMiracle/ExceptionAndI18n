package com.example.demo.service.impl;

import com.example.demo.config.enums.ApiResponseCode;
import com.example.demo.model.constant.Constant;
import com.example.demo.util.exception.RestException;
import com.example.demo.model.Student;
import com.example.demo.service.testApiService;
import org.springframework.stereotype.Service;

@Service
public class testApiServiceImpl implements testApiService {
    @Override
    public Student testException() {
        Student abc =  Student.builder()
                .name(Constant.NAME_USER)
                .age(Constant.AGE_USER)
                .build();
        if (1 == 1){
            throw new RestException(ApiResponseCode.TEST_I18N);
        }
        return abc;
    }
}

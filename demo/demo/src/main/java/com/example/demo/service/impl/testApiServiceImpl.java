package com.example.demo.service.impl;

import com.example.demo.config.enums.ApiResponseCode;
import com.example.demo.util.exception.RestException;
import com.example.demo.model.Student;
import com.example.demo.service.testApiService;
import org.springframework.stereotype.Service;

@Service
public class testApiServiceImpl implements testApiService {
    @Override
    public Student testException() {
        Student abc =  new Student();
        abc.setAge(25);
        abc.setName("kien");
        if (1 == 1){
            throw new RestException(ApiResponseCode.TEST_I18N);
        }

        return abc;
    }
}

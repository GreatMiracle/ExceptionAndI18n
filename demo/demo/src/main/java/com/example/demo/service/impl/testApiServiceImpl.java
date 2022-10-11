package com.example.demo.service.impl;

import com.example.demo.config.enums.ApiResponseCode;
import com.example.demo.model.constant.Constant;
import com.example.demo.util.exception.RestException;
import com.example.demo.model.Student;
import com.example.demo.service.testApiService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

//            List<String> nameAcc = new ArrayList<>();
//            nameAcc.add("sieu@gmail.com");
//            nameAcc.add("kien@gmail.com");
//
//            List<String> lsNameDevice = new ArrayList<>();
//
//
//
//            List<String> lsNewDevice = List.of("iphone8", "samsung", "win10","realme", "samsung");
//
//            Map<String, List<String>> accAllow = new HashMap<>();
//            for (String acc: nameAcc) {
//                accAllow.put(acc, lsNameDevice);
//
//            }
//
//            for (Map.Entry<String, List<String>> entry : accAllow.entrySet()) {
//                for (String newDv : lsNewDevice) {
//                    if (entry.getValue().size()<3 && !entry.getValue().contains(newDv)){
//                        entry.getValue().add(newDv);
//                        System.out.println("thêm thiết bị vào danh sách tin cậy và cho đăng nhâp");
//                    }
//
//                    if (entry.getValue().size()>=3 ){
//
//                        if (entry.getValue().contains(newDv)){
//                            System.out.println(" thiết bị tin cậy đã tồn tại trong danh sách nên cho đăng nhập");
//                        }else {
//                            System.out.println("cút");
//                        }
//
//                    }
//                }
//
//            }



        }
        return abc;
    }

}

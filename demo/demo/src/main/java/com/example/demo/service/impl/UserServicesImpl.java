package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.respository.UserRepository;
import com.example.demo.service.ExportService;
import com.example.demo.service.UserServices;
import com.example.demo.web.rest.dto.UserExportDTO;
import com.example.demo.web.rest.dto.UserRegisterDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServicesImpl implements UserServices {
    private final UserRepository userRepository;
    private final ExportService simpleExportService;
    public List<User> listAll() {
        List<User> listUser = userRepository.findAll(Sort.by("email").ascending());
        List<String> listUser1 = userRepository.listUser1();
        return listUser;
    }

    @Override
    public String listAllKien() {

        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
        String fileName = String.format("%s_%s_%S.xlsx", "TEST_CONFIG_EXCEL", date, time);
        String[] columnName = {"id","email","fullName","roles","enabled"};


        List<User> listUser =  listAll();
        UserExportDTO us = new UserExportDTO();
        List<UserExportDTO> dto = listUser.stream().map(UserServicesImpl::mapUser).collect(Collectors.toList());

        simpleExportService
                .exportPersonalized(fileName, dto, UserExportDTO.class, columnName);

        return fileName;
    }

    @Override
    public boolean checkEmailUserIsExist(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User saveUser(UserRegisterDTO userRegister) {
        User u = User.builder()
                .email(userRegister.getEmail())
                .password(userRegister.getPassword())
                .fullName(userRegister.getFullName())
                .enabled(true)
                .build();
        return userRepository.save(u);
    }

    public static UserExportDTO mapUser(User u){
        return UserExportDTO.builder()
                .id(u.getId())
                .email(u.getEmail())
                .fullName(u.getFullName())
                .roles(u.getEmail())
                .enabled(u.isEnabled())
                .build();
    }
}

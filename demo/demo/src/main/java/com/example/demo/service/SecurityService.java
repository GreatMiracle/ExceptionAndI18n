package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.web.rest.dto.ChangePasswordRequestDTO;
import com.example.demo.web.rest.dto.UserRegisterDTO;

import javax.servlet.http.HttpServletRequest;

public interface SecurityService {
    User registerUser(UserRegisterDTO userAdmin, HttpServletRequest request);

    User changePassword(ChangePasswordRequestDTO request);
}

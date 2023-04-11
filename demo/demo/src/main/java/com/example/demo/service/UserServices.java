package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.web.rest.dto.UserRegisterDTO;

import java.util.List;

public interface UserServices {
    public List<User> listAll();
    public String listAllKien();

    boolean checkEmailUserIsExist(String username);
    User saveUser(UserRegisterDTO userRegister);

}

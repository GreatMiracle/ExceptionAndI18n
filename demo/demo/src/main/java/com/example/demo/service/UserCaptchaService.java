package com.example.demo.service;

import com.example.demo.model.UserCaptcha;

import java.util.List;
import java.util.Optional;

public interface UserCaptchaService {
    void createUser(UserCaptcha user);
    List<UserCaptcha> getAllUsers();
    Optional<UserCaptcha> getOneUser(Integer Id);
}

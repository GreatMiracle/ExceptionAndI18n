package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.respository.UserRepository;

import java.util.List;

public interface UserServices {
    public List<User> listAll();
    public String listAllKien();
}

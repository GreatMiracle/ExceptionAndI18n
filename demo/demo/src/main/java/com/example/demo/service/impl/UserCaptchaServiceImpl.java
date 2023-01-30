package com.example.demo.service.impl;

import com.example.demo.model.UserCaptcha;
import com.example.demo.respository.UserCaptchaRepository;
import com.example.demo.service.UserCaptchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCaptchaServiceImpl implements UserCaptchaService {
    private final UserCaptchaRepository repo;

    @Override
    public void createUser(UserCaptcha user) {

        repo.save(user);
    }

    @Override
    public List<UserCaptcha> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public Optional<UserCaptcha> getOneUser(Integer id) {
        return repo.findById(id);
    }
}

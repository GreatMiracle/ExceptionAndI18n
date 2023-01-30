package com.example.demo.web.rest;

import cn.apiclub.captcha.Captcha;
import com.example.demo.model.UserCaptcha;
import com.example.demo.service.UserCaptchaService;
import com.example.demo.service.ValidateCaptcha;
import com.example.demo.util.BodyResponseDTO;
import com.example.demo.util.CaptchaUtil;
import com.example.demo.util.RestResponseWrapper;
import com.example.demo.web.rest.dto.CapchaRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/captcha")
public class CaptchaResource {
    private final ValidateCaptcha service;
    private final UserCaptchaService userCaptchaService;


    @PostMapping("/test-captcha-robot")
    public ResponseEntity<BodyResponseDTO<Object>> captchaRobot(@RequestBody CapchaRequestDto dto){
        return RestResponseWrapper.getSuccess(service.validateCaptcha(dto.getCaptchaResponse()));
    }


    @GetMapping("/register")
    public String registerUser(Model model) {
        UserCaptcha user = new UserCaptcha();
        getCaptcha(user);
        model.addAttribute("user", user);
        return "registerUser";
    }

    @PostMapping("/save")
    public String saveUser(
            @ModelAttribute UserCaptcha user,
            Model model
    ) {
        if(user.getCaptcha().equals(user.getHiddenCaptcha())) {
//            userCaptchaService.createUser(user);
            model.addAttribute("message", "User Registered successfully!");
            return "redirect:allUsers";
        }
        else {
            model.addAttribute("message", "Invalid Captcha");
            getCaptcha(user);
            model.addAttribute("user", user);
        }
        return "registerUser";
    }

    @GetMapping("/allUsers")
    public String getAllUsers(Model model) {
        List<UserCaptcha> userList= userCaptchaService.getAllUsers();
        model.addAttribute("userList", userList);
        return "listUsers";
    }

    private void getCaptcha(UserCaptcha user) {
        Captcha captcha = CaptchaUtil.createCaptcha(240, 70);
        user.setHiddenCaptcha(captcha.getAnswer());
        user.setCaptcha(""); // value entered by the User
        user.setRealCaptcha(CaptchaUtil.encodeCaptcha(captcha));

    }

}

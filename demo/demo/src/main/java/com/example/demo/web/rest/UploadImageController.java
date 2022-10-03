package com.example.demo.web.rest;

import com.example.demo.model.User;
import com.example.demo.respository.UserRepository;
import com.example.demo.service.UserServices;
import com.example.demo.util.FileUploadUtil;
import com.example.demo.util.MessageUtils;
import com.example.demo.web.rest.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class UploadImageController {

    private final MessageUtils messageUtils;
    private final UserRepository userRepository;

    @PostMapping(value = "/upload/image", consumes = "multipart/form-data")
//    public void saveUser(@RequestPart UserDTO user, @RequestPart("image") MultipartFile multipartFile) throws IOException {
    public void saveUser(@ModelAttribute UserDTO user) throws IOException {
        String fileName = StringUtils.cleanPath(user.getAvatar().getOriginalFilename());

        User updateUser = User.builder().id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .password(user.getPassword())
                .enabled(user.isEnabled())
                .avatar(fileName)
                .build();

        User savedUser = userRepository.save(updateUser);

        String uploadDir = "user-photos/" + savedUser.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, user.getAvatar());

    }
}


package com.example.demo.service.impl;

import com.example.demo.config.enums.ApiResponseCode;
import com.example.demo.model.User;
import com.example.demo.respository.UserRepository;
import com.example.demo.service.SecurityService;
import com.example.demo.service.UserServices;
import com.example.demo.util.Base64Common;
import com.example.demo.util.exception.RestException;
import com.example.demo.web.rest.dto.ChangePasswordRequestDTO;
import com.example.demo.web.rest.dto.UserRegisterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SecurityServiceImpl implements SecurityService {

    private final UserServices userServices;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Override
    public User registerUser(UserRegisterDTO userAdmin, HttpServletRequest request) {
        String uri = request.getRequestURI();
        log.info(uri + " register user admin");

        userAdmin.setEmail(Strings.trimToNull(userAdmin.getEmail()));
        userAdmin.setPassword(Strings.trimToNull(userAdmin.getPassword()));
        userAdmin.setFullName(Strings.trimToNull(userAdmin.getFullName()));

        if (userAdmin.getEmail() == null || userAdmin.getPassword() == null) {
            log.error(uri + " username or password is empty");
            throw new RestException(ApiResponseCode.USERNAME_OR_PASSWORD_EMPTY);
        }

//        String username = Base64Common.decodeBaseToString(userAdmin.getEmail());
//        String pwd = Base64Common.decodeBaseToString(userAdmin.getPassword());
        String pwd = userAdmin.getPassword();

        if (userServices.checkEmailUserIsExist(userAdmin.getEmail())) {
            log.error(uri + " username is exist");
            throw new RestException(ApiResponseCode.USERNAME_EXIST);
        }

        userAdmin.setPassword(passwordEncoder.encode(pwd));

        User result = this.userServices.saveUser(userAdmin);
        return result;
    }

    @Override
    public User changePassword(ChangePasswordRequestDTO request) {
        User updatePwd;
        User u = userRepository.findByEmailAndEnabledIsTrue(request.getEmail()).orElseThrow(() -> new RestException(ApiResponseCode.USER_NOT_EXIST));
//        String pwd = Base64Common.decodeBaseToString(request.getCurrentPassword());

        if (BCrypt.checkpw(request.getCurrentPassword(), u.getPassword())) {
            if(request.getNewPassword().equals(request.getConfirmPassword())){
                u.setPassword(passwordEncoder.encode(request.getNewPassword()));
                updatePwd = userRepository.save(u);
            }else {
                throw new RestException(ApiResponseCode.PASSWORD_NOT_SAME);
            }
        }else {
            throw new RestException(ApiResponseCode.PASSWORD_NOT_SAME);
        }
        return updatePwd;
    }
}

package com.example.demo.web.rest;

import com.example.demo.config.enums.ApiResponseCode;
import com.example.demo.model.User;
import com.example.demo.security.AuthenticationToken;
import com.example.demo.security.jwt.TokenProvider;
import com.example.demo.service.UserServices;
import com.example.demo.util.Base64Common;
import com.example.demo.util.BodyResponseDTO;
import com.example.demo.util.MessageUtils;
import com.example.demo.util.RestResponseWrapper;
import com.example.demo.util.exception.RestException;
import com.example.demo.web.rest.dto.JwtResponse;
import com.example.demo.web.rest.dto.LoginRequest;
import com.example.demo.web.rest.dto.UserRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class SecurityController {

    Logger log = LoggerFactory.getLogger(SecurityController.class);
    private final MessageUtils messageUtils;
    private final AuthenticationProvider authenticationProvider;
    private final TokenProvider tokenProvider;
    private final UserServices userServices;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/sign-in")
    public ResponseEntity<BodyResponseDTO<JwtResponse>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//        AuthenticationProvider authenticationProvider = null;
        Authentication authentication = authenticationProvider.authenticate(new AuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        String accessToken = tokenProvider.createToken(authentication, false);
//        String refreshToken = tokenProvider.createToken(authentication, true);

        return RestResponseWrapper.getSuccess(new JwtResponse(accessToken));

    }


    @PostMapping(value = "/register")
    public ResponseEntity<BodyResponseDTO<User>> register(@RequestBody UserRegisterDTO userAdmin,
                                                          HttpServletRequest request) {
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
        String pwd = Base64Common.decodeBaseToString(userAdmin.getPassword());

        if (userServices.checkEmailUserIsExist(userAdmin.getEmail())) {
            log.error(uri + " username is exist");
            throw new RestException(ApiResponseCode.USERNAME_EXIST);
        }

        userAdmin.setPassword(passwordEncoder.encode(pwd));

        User result = this.userServices.saveUser(userAdmin);
        return RestResponseWrapper.getSuccess(result, messageUtils);
    }





}

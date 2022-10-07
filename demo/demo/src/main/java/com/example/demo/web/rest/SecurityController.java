package com.example.demo.web.rest;

import com.example.demo.security.AuthenticationToken;
import com.example.demo.security.jwt.TokenProvider;
import com.example.demo.util.BodyResponseDTO;
import com.example.demo.util.RestResponseWrapper;
import com.example.demo.web.rest.dto.JwtResponse;
import com.example.demo.web.rest.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class SecurityController {
    private final AuthenticationProvider authenticationProvider;
    private final TokenProvider tokenProvider;

    @PostMapping("/sign-in")
    public ResponseEntity<BodyResponseDTO<JwtResponse>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//        AuthenticationProvider authenticationProvider = null;
        Authentication authentication = authenticationProvider.authenticate(new AuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        String accessToken = tokenProvider.createToken(authentication, false);
//        String refreshToken = tokenProvider.createToken(authentication, true);

        return RestResponseWrapper.getSuccess(new JwtResponse(accessToken));

    }





}

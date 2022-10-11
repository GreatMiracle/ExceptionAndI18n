package com.example.demo.web.rest.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginRequest {
    private String username;
    private String password;

}

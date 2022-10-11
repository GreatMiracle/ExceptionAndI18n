package com.example.demo.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long userId;
    private String userName;
    private String fullName;
    private String email;
    private Collection<?> role;
    private Collection<?> urlEndpoints;
    private Boolean firstLogin;
    private Boolean isChangePwd;
    private Boolean isNormalTwoFactorAuthUsed;
}

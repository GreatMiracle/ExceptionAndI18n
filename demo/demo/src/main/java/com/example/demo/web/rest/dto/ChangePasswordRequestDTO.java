package com.example.demo.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangePasswordRequestDTO {

    @NotEmpty(message = "GLOBAL.FIELD_NOT_NULL")
    private String email;
    @NotEmpty(message = "GLOBAL.FIELD_NOT_NULL")
    private String currentPassword;
    @NotEmpty(message = "GLOBAL.FIELD_NOT_NULL")
    private String newPassword;
    @NotEmpty(message = "GLOBAL.FIELD_NOT_NULL")
    private String confirmPassword;
}
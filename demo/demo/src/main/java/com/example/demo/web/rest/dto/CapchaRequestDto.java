package com.example.demo.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

// Lombok annotations
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CapchaRequestDto {

    @NonNull
    String name;
    @NonNull
    String captchaResponse;
}

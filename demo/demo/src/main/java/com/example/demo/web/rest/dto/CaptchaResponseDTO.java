package com.example.demo.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CaptchaResponseDTO {
    boolean success;
    LocalDateTime challenge_ts;
    String hostname;
    @JsonProperty("error-codes")
    List<String> errorCodes;
}

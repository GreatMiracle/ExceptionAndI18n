package com.example.demo.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReadEnvironment {
    @Value("${jwt.secret}")
    private String base64Secret;

    @Value("${jwt.expiration}")
    private long tokenValidityInSeconds;

    public String getBase64Secret() {
        return base64Secret;
    }

    public long getTokenValidityInSeconds() {
        return tokenValidityInSeconds;
    }

    public ReadEnvironment() {
    }

    public ReadEnvironment(String base64Secret, long tokenValidityInSeconds) {
        this.base64Secret = base64Secret;
        this.tokenValidityInSeconds = tokenValidityInSeconds;

    }
}

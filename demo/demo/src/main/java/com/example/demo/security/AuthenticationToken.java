package com.example.demo.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthenticationToken extends AbstractAuthenticationToken {
    private String username;
    private String password;

    public AuthenticationToken(String username, String password) {
        super((Collection)null);
//        this.password =  password + username;
        this.password =  password;
        this.username = username;
        this.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return username;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

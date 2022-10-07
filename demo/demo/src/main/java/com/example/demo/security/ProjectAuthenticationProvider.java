package com.example.demo.security;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import com.example.demo.model.User;
import com.example.demo.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Component()
@RequiredArgsConstructor
@Slf4j
public class  ProjectAuthenticationProvider implements AuthenticationProvider {
    private final UserRepository userRepository;
    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AuthenticationToken authenticationToken = (AuthenticationToken) authentication;
        String username = authenticationToken.getPrincipal().toString();
        Optional<User> authenticatedUser = userRepository.findByEmailAndEnabledIsTrue(username);

        if (authenticatedUser.isPresent()) {
//            if (BCrypt.checkpw(authenticationToken.getPassword(), authenticatedUser.get().getPassword())) {
            if (authenticationToken.getPassword().equals(authenticatedUser.get().getPassword().concat(authenticatedUser.get().getEmail()))) {
                UserPrincipal userDetails = UserPrincipal.create(authenticatedUser.get());
                return new UsernamePasswordAuthenticationToken(authenticatedUser.get(), authentication.getCredentials(), userDetails.getAuthorities());
            } else {
                throw new BadCredentialsException("User not found");
            }
        } else {
            throw new BadCredentialsException("User not found");
        }
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}

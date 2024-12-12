package com.spring.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.spring.app.utils.AuthenticationContextUtil;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication authenticationResult = customAuthenticationProvider.authenticate(authentication);
        AuthenticationContextUtil.setAuthenticationContext(authenticationResult);
        return AuthenticationContextUtil.getAuthenticationContext();
    }
    
}

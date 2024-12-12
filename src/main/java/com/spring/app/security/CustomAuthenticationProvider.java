package com.spring.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.spring.app.services.AccountAttempService;
import com.spring.app.services.PasswordService;
import com.spring.app.services.UserRepositoryService;
import com.spring.app.utils.AuthenticationContextUtil;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private AccountAttempService accountAttempService;

    @Autowired
    private PasswordService passwordService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        CustomUserDetails customUserDetails = (CustomUserDetails) userRepositoryService.loadUserByUsername(username);

        accountAttempService.checkDateLockIsExpired(customUserDetails);
        
        if (!passwordService.comparePassword(password, customUserDetails.getPassword())) {
            accountAttempService.checkAttemps(customUserDetails);
            throw new BadCredentialsException("Bad credentials");
        }

        UsernamePasswordAuthenticationToken userAuthentication = new UsernamePasswordAuthenticationToken(
            customUserDetails.getUsername(),
            customUserDetails.getPassword(),
            customUserDetails.getAuthorities()
        );

        AuthenticationContextUtil.setAuthenticationContext(userAuthentication);
        return AuthenticationContextUtil.getAuthenticationContext();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}

package com.spring.app.mocks.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.spring.app.entity.Users;
import com.spring.app.mocks.entities.UserMock;
import com.spring.app.security.CustomUserDetails;

public abstract class AuthenticationMock {
    
    public static Authentication getAuthentication() {
        Users user = UserMock.getUser();
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(
            customUserDetails.getUsername(), customUserDetails.getPassword(), customUserDetails.getRoles());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);
        return securityContext.getAuthentication(); 
    }
}

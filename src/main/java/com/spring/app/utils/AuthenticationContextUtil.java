package com.spring.app.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AuthenticationContextUtil {
    public static Authentication getAuthenticationContext() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static void setAuthenticationContext(final Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}

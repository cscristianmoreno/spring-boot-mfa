package com.spring.app.models.services;

import org.springframework.security.core.AuthenticationException;

import com.spring.app.security.CustomUserDetails;

public interface IAccountAttempService {
    void checkAttemps(CustomUserDetails authentication);

    void checkDateLockIsExpired(CustomUserDetails customUserDetails) throws AuthenticationException;
}

package com.spring.app.exceptions;

import org.springframework.security.core.AuthenticationException;

public class AccountLockedException extends AuthenticationException {
    
    public AccountLockedException(String msg) {
        super(msg);
    }
}

package com.spring.app.mocks.login;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public abstract class LoginMock {
    
    public static UsernamePasswordAuthenticationToken getLogin() {
        return new UsernamePasswordAuthenticationToken("test", "test");
    }
}

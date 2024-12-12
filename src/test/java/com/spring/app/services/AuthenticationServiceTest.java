package com.spring.app.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.spring.app.mocks.authentication.AuthenticationMock;
import com.spring.app.mocks.login.LoginMock;
import com.spring.app.security.CustomAuthenticationManager;

@SpringBootTest
public class AuthenticationServiceTest {

    @MockBean
    private CustomAuthenticationManager customAuthenticationManager;

    @Autowired
    private AuthenticationService authenticationService;

    private UsernamePasswordAuthenticationToken auth;

    @BeforeEach
    void setup() {
        auth = LoginMock.getLogin();
    }

    @Test
    void testAuthenticate() {
        given(customAuthenticationManager.authenticate(auth)).willReturn(AuthenticationMock.getAuthentication());
        
        String token = authenticationService.authenticate(auth);

        System.out.println(token);
        assertNotNull(token);
    }
}

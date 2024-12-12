package com.spring.app.security;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.spring.app.mocks.authentication.AuthenticationMock;
import com.spring.app.mocks.login.LoginMock;

@SpringBootTest
public class CustomAuthenticationManagerTest {

    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;

    @MockBean
    private CustomAuthenticationProvider customAuthenticationProvider;

    private Authentication authentication;
    private UsernamePasswordAuthenticationToken login;

    @BeforeEach
    void setup() {
        authentication = AuthenticationMock.getAuthentication(); 

        login = LoginMock.getLogin();
    }

    @Test
    void testAuthenticate() {
        given(customAuthenticationProvider.authenticate(login)).willReturn(authentication);

        Authentication result = customAuthenticationManager.authenticate(login);
        
        System.out.println(result);
        assertNotNull(result);
    }
}

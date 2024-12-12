package com.spring.app.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;

import com.spring.app.mocks.authentication.AuthenticationMock;

@SpringBootTest
@ContextConfiguration
public class JwkServiceTest {
    @Autowired
    private JwkService jwkService;
    
    private Authentication authentication;
    
    @BeforeEach
    void setup() {
        authentication = AuthenticationMock.getAuthentication();
    }
    
    @Test
    void testGetToken() {
        String token = jwkService.getToken(authentication);

        System.out.println(token);

        assertNotNull(token);
    }

    @Test
    void testGetClaims() {
        String token = jwkService.getToken(authentication);

        Map<String, Object> jwt = jwkService.getClaims(token);

        assertNotNull(jwt);
    }
}

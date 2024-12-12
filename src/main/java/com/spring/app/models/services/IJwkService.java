package com.spring.app.models.services;

import java.util.Map;

import org.springframework.security.core.Authentication;

public interface IJwkService {
    String getToken(Authentication authentication);

    Map<String, Object> getClaims(String token);
}

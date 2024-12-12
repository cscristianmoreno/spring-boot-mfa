package com.spring.app.models.services;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import jakarta.servlet.http.HttpServletResponse;

public interface IAuthenticationService {
    String authenticate(UsernamePasswordAuthenticationToken auth);

    void generateQRCode(String userName, HttpServletResponse response) throws Exception;

    boolean verifyQRCode(String userName, int code);
}

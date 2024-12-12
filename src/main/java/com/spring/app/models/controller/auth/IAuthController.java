package com.spring.app.models.controller.auth;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.zxing.WriterException;
import com.spring.app.dto.GoogleAuthenticatorCode;
import com.spring.app.dto.LoginDTO;
import com.spring.app.dto.ResponseEntityDTO;

import jakarta.servlet.http.HttpServletResponse;

public interface IAuthController {
    @PostMapping("/login")
    ResponseEntity<ResponseEntityDTO> login(@RequestBody LoginDTO loginDTO);

    @PostMapping("/qr")
    void generateQRCode(@RequestBody Map<String, String> body, HttpServletResponse httpServletResponse) throws WriterException, IOException, Exception;

    @PostMapping("/code")
    ResponseEntity<ResponseEntityDTO> verifyCode(@RequestBody GoogleAuthenticatorCode googleAuthenticatorCode);
}

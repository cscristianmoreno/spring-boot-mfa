package com.spring.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.spring.app.models.services.IAuthenticationService;
import com.spring.app.security.CustomAuthenticationManager;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class AuthenticationService implements IAuthenticationService {
    
    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;

    @Autowired
    private JwkService jwkService;

    @Autowired
    private GoogleAuthenticator googleAuthenticator;

    @Override
    public String authenticate(UsernamePasswordAuthenticationToken auth) {
        Authentication authentication = customAuthenticationManager.authenticate(auth); 
        return jwkService.getToken(authentication);
    }

    @Override
    public void generateQRCode(String userName, HttpServletResponse response) throws Exception {
        GoogleAuthenticatorKey googleAuthenticatorKey = googleAuthenticator.createCredentials(userName);

        String qrGenerated = GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL("spring-app", userName, googleAuthenticatorKey);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        
        BitMatrix bitMatrix = qrCodeWriter.encode(qrGenerated, BarcodeFormat.QR_CODE, 200, 200);
        
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", response.getOutputStream());
    }

    @Override
    public boolean verifyQRCode(String userName, int code) {
        return googleAuthenticator.authorizeUser(userName, code);
    }
    
}

package com.spring.app.controller.auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.app.dto.GoogleAuthenticatorCode;
import com.spring.app.dto.LoginDTO;
import com.spring.app.dto.ResponseEntityDTO;
import com.spring.app.models.controller.auth.IAuthController;
import com.spring.app.services.AuthenticationService;
import com.spring.app.utils.ResponseEntityUtil;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@ResponseBody
@RequestMapping("/auth")
public class AuthController implements IAuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public ResponseEntity<ResponseEntityDTO> login(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
            loginDTO.getUsername(),
            loginDTO.getPassword()
        );

        String token = authenticationService.authenticate(auth);
        return ResponseEntityUtil.success(token);
    }

    @Override
    public void generateQRCode(Map<String, String> body, HttpServletResponse response) throws Exception {
        String userName = body.get("username");
        authenticationService.generateQRCode(userName, response);
    }

    @Override
    public ResponseEntity<ResponseEntityDTO> verifyCode(GoogleAuthenticatorCode googleAuthenticatorCode) {
        int code = googleAuthenticatorCode.getCode();
        String username = googleAuthenticatorCode.getUsername();
        return ResponseEntityUtil.success(authenticationService.verifyQRCode(username, code));
    }
}

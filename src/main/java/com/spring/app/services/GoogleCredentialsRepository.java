package com.spring.app.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.app.entity.UserTOTP;
import com.warrenstrange.googleauth.ICredentialRepository;

@Service
public class GoogleCredentialsRepository implements ICredentialRepository {

    private final Map<String, UserTOTP> usersCode = new HashMap<String, UserTOTP>();

    @Autowired
    private GoogleAuthenticatorRepositoryService googleAuthenticatorRepositoryService;

    @Override
    public String getSecretKey(String userName) {
        Optional<UserTOTP> result = googleAuthenticatorRepositoryService.findByUsername(userName);

        if (result.isEmpty()) {
            throw new UsernameNotFoundException("Username TOTP not found");
        }

        UserTOTP userTOTP = result.get();
        return userTOTP.getSecretKey();
    }

    @Override
    public void saveUserCredentials(String userName, String secretKey, int validationCode, List<Integer> scratchCodes) {
        UserTOTP userTOTP = new UserTOTP();
        userTOTP.setUsername(userName);
        userTOTP.setSecretKey(secretKey);
        userTOTP.setScratchCodes(scratchCodes);
        
        googleAuthenticatorRepositoryService.save(userTOTP);
    }
    
    public UserTOTP getUser(String username) {

        return usersCode.get(username);
    }
}

package com.spring.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.app.entity.Users;
import com.spring.app.exceptions.ChangePasswordException;
import com.spring.app.models.services.IPasswordService;

@Service
public class PasswordService implements IPasswordService {

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public String changePassword(String username, String lastPassword, String newPassword) {
        Optional<Users> result = userRepositoryService.findByUsername(username); 
        Users users = result.get();
        String encodedPassword = users.getPassword();

        if (!comparePassword(lastPassword, encodedPassword)) {
            throw new ChangePasswordException("Password not matches");
        }

        String updatedPassword = passwordEncoder.encode(newPassword);
        users.setPassword(updatedPassword);
        userRepositoryService.update(users);
        return updatedPassword;
    }

    @Override
    public boolean comparePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    
}

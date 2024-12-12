package com.spring.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.app.entity.UserTOTP;
import com.spring.app.models.services.IGoogleAuthenticatorRepositoryService;
import com.spring.app.repository.GoogleAuthenticatorRepository;

@Service
public class GoogleAuthenticatorRepositoryService implements IGoogleAuthenticatorRepositoryService {
    
    @Autowired
    private GoogleAuthenticatorRepository googleAuthenticatorRepository;

    @Override
    public UserTOTP save(UserTOTP entity) {
        return googleAuthenticatorRepository.save(entity);
    }

    @Override
    public UserTOTP update(UserTOTP entity) {
        return googleAuthenticatorRepository.save(entity);
    }

    @Override
    public Optional<UserTOTP> findById(int id) {
        return googleAuthenticatorRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        googleAuthenticatorRepository.deleteById(id);
    }

    @Override
    public Optional<UserTOTP> findByUsername(String username) {
        return googleAuthenticatorRepository.findByUsername(username);
    }
}

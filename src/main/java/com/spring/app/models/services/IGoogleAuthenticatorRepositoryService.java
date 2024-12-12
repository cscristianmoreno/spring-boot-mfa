package com.spring.app.models.services;

import java.util.Optional;

import com.spring.app.entity.UserTOTP;
import com.spring.app.models.repository.IRepository;

public interface IGoogleAuthenticatorRepositoryService extends IRepository<UserTOTP> {
    Optional<UserTOTP> findByUsername(String username);
}

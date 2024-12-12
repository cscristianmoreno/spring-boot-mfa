package com.spring.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import com.spring.app.entity.UserTOTP;

@EnableJpaRepositories
public interface GoogleAuthenticatorRepository extends CrudRepository<UserTOTP, Integer> {
    Optional<UserTOTP> findByUsername(String username);
}

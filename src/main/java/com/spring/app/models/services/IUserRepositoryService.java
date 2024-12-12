package com.spring.app.models.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.spring.app.entity.Users;
import com.spring.app.models.repository.IRepository;

public interface IUserRepositoryService extends IRepository<Users>, UserDetailsService {
    Optional<Users> findByUsername(String username);
}

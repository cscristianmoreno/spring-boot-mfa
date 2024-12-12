package com.spring.app.models.services;

import java.time.LocalDateTime;

import com.spring.app.entity.Accounts;
import com.spring.app.models.repository.IRepository;

public interface IAccountRepositoryService extends IRepository<Accounts> {
    void updateIsAccountNonLocked(int id);

    void updateIsAccountLocked(int id, LocalDateTime duration);

    void updatePlusAttemps(int id);
}

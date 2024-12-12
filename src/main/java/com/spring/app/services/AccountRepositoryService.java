package com.spring.app.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.app.entity.Accounts;
import com.spring.app.models.services.IAccountRepositoryService;
import com.spring.app.repository.AccountRepository;

@Service
public class AccountRepositoryService implements IAccountRepositoryService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Accounts save(Accounts entity) {
        return accountRepository.save(entity);
    }

    @Override
    public Accounts update(Accounts entity) {
        return accountRepository.save(entity);
    }

    @Override
    public Optional<Accounts> findById(int id) {
        return accountRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void updateIsAccountNonLocked(int id) {
        accountRepository.updateIsAccountNonLocked(id);
    }

    @Override
    public void updateIsAccountLocked(int id, LocalDateTime duration) {
        accountRepository.updateIsAccountLocked(id, duration);
    }

    @Override
    public void updatePlusAttemps(int id) {
        accountRepository.updatePlusAttemps(id);
    }
    
}

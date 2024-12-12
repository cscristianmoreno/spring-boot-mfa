package com.spring.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.spring.app.entity.Accounts;
import com.spring.app.entity.Users;
import com.spring.app.models.services.IUserRepositoryService;
import com.spring.app.repository.UserRepository;
import com.spring.app.security.CustomUserDetails;

@Service
public class UserRepositoryService implements IUserRepositoryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepositoryService accountRepositoryService;

    @Autowired
    @Lazy
    private PasswordService passwordService;

    @Override
    public Users save(Users entity) {
        String password = entity.getPassword();

        Accounts account = getAccount();

        entity.setPassword(passwordService.encode(password));
        entity.setAccount(account);
        return userRepository.save(entity);
    }

    @Override
    public Users update(Users entity) {
        return userRepository.save(entity);
    }

    @Override
    public Optional<Users> findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<Users> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Username not found");
        }
        
        return new CustomUserDetails(user.get());
    }

    private Accounts getAccount() {
        Accounts accounts = new Accounts();
        return accountRepositoryService.save(accounts);
    }
}

package com.spring.app.mocks.entities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spring.app.entity.Users;

public abstract class UserMock {
    
    public static Users getUser() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Users users = new Users();
        users.setUsername("test");
        users.setPassword(passwordEncoder.encode("test"));
        users.setEmail("test@gmail.com");
        users.setRole(RoleMock.getRole());
        users.setAccount(AccountMock.getAccount());
        return users;
    }
}

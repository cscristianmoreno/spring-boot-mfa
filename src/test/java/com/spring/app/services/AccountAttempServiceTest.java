package com.spring.app.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.spring.app.entity.Users;
import com.spring.app.mocks.entities.UserMock;
import com.spring.app.repository.UserRepository;
import com.spring.app.security.CustomUserDetails;

@SpringBootTest
public class AccountAttempServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountAttempService accountAttempService;

    private Users users;

    @BeforeEach
    void setup() {
        users = UserMock.getUser();
    }

    @Test
    void testLock() {
        userRepository.save(users);

        CustomUserDetails customUserDetails = new CustomUserDetails(users);

        accountAttempService.checkAttemps(customUserDetails);
    }
}

package com.spring.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.spring.app.entity.Users;
import com.spring.app.mocks.entities.UserMock;

@SpringBootTest
public class PasswordServiceTest {

    @MockBean
    private UserRepositoryService userRepositoryService;

    @Autowired
    private PasswordService passwordService;

    private Users users;

    @BeforeEach
    void setup() {
        users = UserMock.getUser();
    }

    @Test
    void testChangePassword() {
        given(userRepositoryService.findByUsername("test")).willReturn(Optional.of(users));

        String password = passwordService.changePassword("test", "test", "newPassword");

        assertNotNull(password);
        assertTrue(passwordService.comparePassword("newPassword", password));
    }
}

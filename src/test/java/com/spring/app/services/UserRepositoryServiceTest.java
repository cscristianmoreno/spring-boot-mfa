package com.spring.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.app.annotation.PgResetSequence;
import com.spring.app.entity.Users;
import com.spring.app.exceptions.RepositoryInterceptorException;
import com.spring.app.mocks.entities.UserMock;
import com.spring.app.repository.UserRepository;
import com.spring.app.security.CustomUserDetails;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@PgResetSequence
public class UserRepositoryServiceTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserRepositoryService userRepositoryService;

    private Users users;

    @BeforeEach
    public void setup() {
        users = UserMock.getUser();
    }

    @Test
    @Order(1)
    void testSave() {
        given(userRepository.save(users)).willReturn(users);

        Users result = userRepositoryService.save(users);
        
        System.out.println(result);
        assertNotNull(result);
        assertEquals(users, result);
    }
    
    @Test
    @Order(2)
    void testUpdate() {
        given(userRepository.save(users)).willReturn(users);

        users.setId(1);
        users.setEmail("robert@gmail.com");
        Users result = userRepositoryService.update(users);
        
        assertNotNull(result);
        assertEquals("robert@gmail.com", result.getEmail());
    }


    @Test
    @Order(3)
    void testUpdateWithoutIdEntity() {
        given(userRepository.save(users)).willReturn(users);

        users.setEmail("robert@gmail.com");
        
        assertThrows(RepositoryInterceptorException.class, () -> {
            userRepositoryService.update(users);
        });
    }

    @Test
    @Order(4)
    void testFindById() {
        given(userRepository.findById(1)).willReturn(Optional.of(users));

        Optional<Users> result = userRepositoryService.findById(1);

        assertNotNull(result);
        assertTrue(result.isPresent());
    }

    @Test
    @Order(5)
    void testLoadByUsername() {
        given(userRepository.findByUsername(users.getUsername())).willReturn(Optional.of(users));

        UserDetails result = userRepositoryService.loadUserByUsername(users.getUsername());

        assertNotNull(result);
        assertTrue(result.getClass().isAssignableFrom(CustomUserDetails.class));
    }

    @Test
    @Order(6)
    void testLoadByUsernameNotFound() {
        given(userRepository.findByUsername(users.getUsername())).willReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userRepositoryService.loadUserByUsername(users.getUsername());
        });
    }

    @Test
    @Order(7)
    void testDeleteById() {
        willDoNothing().given(userRepository).deleteById(1);

        userRepositoryService.deleteById(1);

        verify(userRepository, times(1)).deleteById(1);
    }
}

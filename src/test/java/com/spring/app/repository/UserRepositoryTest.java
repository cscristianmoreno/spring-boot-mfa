package com.spring.app.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.spring.app.annotation.PgResetSequence;
import com.spring.app.entity.Users;
import com.spring.app.mocks.entities.UserMock;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@PgResetSequence
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private Users users;
    private Users result;

    @BeforeEach
    public void setup() {
        users = UserMock.getUser();
        result = userRepository.save(users);
    }

    @Test
    @Order(1)
    void testSave() {
        assertNotNull(result);
        assertEquals(users.getEmail(), result.getEmail());
    }
    
    @Test
    @Order(2)
    void testUpdate() {
       
        users.setId(1);
        users.setEmail("robert@gmail.com");
        Users result = userRepository.save(users);
        
        assertNotNull(result);
        assertEquals(users.getEmail(), result.getEmail());
    }

    @Test
    @Order(3)
    void testFindById() {
        Optional<Users> result = userRepository.findById(1);

        System.out.println(result.get());

        assertNotNull(result);
        assertTrue(result.isPresent());
    }

    @Test
    @Order(4)
    void testFindByUsername() {
        Optional<Users> result = userRepository.findByUsername(users.getUsername());

        assertNotNull(result);
        assertTrue(result.isPresent());
    }

    @Test
    @Order(5)
    void testDeleteById() {
        userRepository.deleteById(1);
        Optional<Users> result = userRepository.findById(1);
        
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}

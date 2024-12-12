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
import com.spring.app.entity.Accounts;
import com.spring.app.mocks.entities.AccountMock;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@PgResetSequence
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    private Accounts accounts;
    private Accounts result;

    @BeforeEach
    public void setup() {
        accounts = AccountMock.getAccount();
        result = accountRepository.save(accounts);
    }

    @Test
    @Order(1)
    void testSave() {
        assertNotNull(result);
    }
    
    @Test
    @Order(2)
    void testUpdate() {
        accounts.setId(1);
        accounts.setEnabled(false);
        Accounts result = accountRepository.save(accounts);
        
        assertNotNull(result);
        assertEquals(false, result.isEnabled());
    }

    @Test
    @Order(3)
    void testFindById() {
        Optional<Accounts> result = accountRepository.findById(1);

        System.out.println(result);

        assertNotNull(result);
        assertTrue(result.isPresent());
    }

    @Test
    @Order(4)
    void testDeleteById() {
        accountRepository.deleteById(1);
        Optional<Accounts> result = accountRepository.findById(1);
        
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}

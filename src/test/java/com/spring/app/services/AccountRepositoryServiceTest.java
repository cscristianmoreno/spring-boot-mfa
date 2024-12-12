package com.spring.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.doNothing;
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

import com.spring.app.entity.Accounts;
import com.spring.app.mocks.entities.AccountMock;
import com.spring.app.repository.AccountRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountRepositoryServiceTest {
    
    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountRepositoryService accountRepositoryService;

    private Accounts account;

    @BeforeEach
    void setup() {
        account = AccountMock.getAccount();
    }

    @Test
    @Order(1)
    void testSave() {
        given(accountRepository.save(account)).willReturn(account);
        
        Accounts result = accountRepositoryService.save(account);

        assertNotNull(result);
    }

    @Test
    @Order(2)
    void testUpdate() {
        account.setEnabled(false);
        account.setId(1);
        given(accountRepository.save(account)).willReturn(account);
        
        Accounts result = accountRepositoryService.update(account);

        assertNotNull(result);
        assertEquals(false, result.isEnabled());
    }

    @Test
    @Order(3)
    void testFindById() {
        given(accountRepository.findById(1)).willReturn(Optional.of(account));

        Optional<Accounts> result = accountRepositoryService.findById(1);

        assertNotNull(result);
        assertTrue(result.isPresent());
    }

    @Test
    @Order(4)
    void testDeleteById() {
        willDoNothing().given(accountRepository).deleteById(1);

        accountRepositoryService.deleteById(1);

        verify(accountRepository, times(1)).deleteById(1);
    }

    @Test
    @Order(5)
    void testUpdateIsAccountNonLocked() {
        willDoNothing().given(accountRepository).updateIsAccountNonLocked(1);

        accountRepository.updateIsAccountNonLocked(1);

        verify(accountRepository, times(1)).updateIsAccountNonLocked(1);
    }

    @Test
    @Order(6)
    void testUpdatePlusAttemps() {
        willDoNothing().given(accountRepository).updatePlusAttemps(1);

        accountRepository.updatePlusAttemps(1);

        verify(accountRepository, times(1)).updatePlusAttemps(1);
    }
}

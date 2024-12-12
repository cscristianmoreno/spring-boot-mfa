package com.spring.app.security;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import com.spring.app.entity.Users;
import com.spring.app.exceptions.AccountLockedException;
import com.spring.app.mocks.entities.UserMock;
import com.spring.app.mocks.login.LoginMock;
import com.spring.app.services.UserRepositoryService;

@SpringBootTest
public class CustomAuthenticationProviderTest {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @MockBean
    private UserRepositoryService userRepositoryService;

    private CustomUserDetails customUserDetails;
    private UsernamePasswordAuthenticationToken login;
    private Users users;

    @BeforeEach
    void setup() {
        users = UserMock.getUser();
        customUserDetails = new CustomUserDetails(users);

        login = LoginMock.getLogin();
    }

    @Test
    void testAuthenticate() {
        given(userRepositoryService.loadUserByUsername("test")).willReturn(customUserDetails);

        Authentication authentication = customAuthenticationProvider.authenticate(login);

        assertNotNull(authentication);
    }

    @Test
    void testAuthenticatePasswordFailed() {
        given(userRepositoryService.loadUserByUsername("test")).willReturn(customUserDetails);
        
        assertThrows(BadCredentialsException.class, () -> {
            login = new UsernamePasswordAuthenticationToken("test", "testt");
            customAuthenticationProvider.authenticate(login);
        });
    }

    @Test
    void testAuthenticateAccountLocked() {
        users.getAccount().setDateLock(LocalDateTime.now().plusMinutes(5));
        users.getAccount().setAccountNonLocked(false);
        customUserDetails = new CustomUserDetails(users);

        given(userRepositoryService.loadUserByUsername("test")).willReturn(customUserDetails);
        
        AccountLockedException exception = assertThrows(AccountLockedException.class, () -> {
            login = new UsernamePasswordAuthenticationToken("test", "testt");
            customAuthenticationProvider.authenticate(login);
        });

        System.out.println(exception);
    }
}

package com.spring.app.services;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.spring.app.exceptions.AccountLockedException;
import com.spring.app.models.services.IAccountAttempService;
import com.spring.app.security.CustomUserDetails;

@Service
public class AccountAttempService implements IAccountAttempService {

    @Autowired
    private AccountRepositoryService accountRepositoryService;

    @Value("${auth.login.attemps}")
    private int maxAttemps;

    @Value("${auth.lock.expire}")
    private int lockExpire;

    @Override
    public void checkAttemps(CustomUserDetails customUserDetails) {
        
        checkDateLockIsExpired(customUserDetails);
        
        if (!customUserDetails.isAccountNonLocked()) {
            return;
        }

        if (customUserDetails.getAttemps() >= (maxAttemps - 1)) {
            LocalDateTime now = LocalDateTime.now();
            accountRepositoryService.updateIsAccountLocked(customUserDetails.getId(), now.plusSeconds(lockExpire));
            return;
        }

        accountRepositoryService.updatePlusAttemps(customUserDetails.getAccount().getId());
    }

    @Override
    public void checkDateLockIsExpired(CustomUserDetails customUserDetails) throws AuthenticationException {
        LocalDateTime lockDate = customUserDetails.getDateLock();
        LocalDateTime localDateTime = LocalDateTime.now();

        if (!customUserDetails.isAccountNonLocked()) {
            if (localDateTime.compareTo(lockDate) < 0) {
                String reason = String.format("Your account has been locked because your exceeded attempts limit. Try a new attempt in : %s", lockDate);
                throw new AccountLockedException(reason);
            }
            else {
                accountRepositoryService.updateIsAccountNonLocked(customUserDetails.getAccount().getId());
            }
        }
    }
}

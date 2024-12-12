package com.spring.app.models.security;

import java.time.LocalDateTime;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.app.entity.Accounts;

public interface IUserDetails extends UserDetails {
    int getId();

    int getAttemps();
    
    Collection<? extends GrantedAuthority> getRoles();
    
    LocalDateTime getDateLock();
    
    Accounts getAccount();
}

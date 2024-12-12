package com.spring.app.security;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.spring.app.entity.Accounts;
import com.spring.app.entity.Users;
import com.spring.app.models.security.IUserDetails;

public class CustomUserDetails implements IUserDetails {

    private final Users users;

    public CustomUserDetails(final Users users) {
        this.users = users;
    }

    @Override
    public int getId() {
        return users.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getRoles() {
        return AuthorityUtils.createAuthorityList(users.getRole().getName().name());
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return users.getAccount().isAccountNonExpired();
    }
  
    @Override
    public boolean isAccountNonLocked() {
        return users.getAccount().isAccountNonLocked();
    }
  
    @Override
    public boolean isCredentialsNonExpired() {
        return users.getAccount().isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return users.getAccount().isEnabled();
    }

    @Override
    public LocalDateTime getDateLock() {
        return users.getAccount().getDateLock();
    }

    @Override
    public int getAttemps() {
        return users.getAccount().getAttemps();
    }

    @Override
    public Accounts getAccount() {
        return users.getAccount();
    }
}

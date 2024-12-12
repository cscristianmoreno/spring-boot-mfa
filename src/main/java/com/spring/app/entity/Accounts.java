package com.spring.app.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Accounts extends BaseEntity {
    private boolean isAccountNonExpired = true; 

    private boolean isAccountNonLocked = true; 

    private boolean isCredentialsNonExpired = true;

    private boolean isEnabled = true;

    private int attemps = 0;

    private LocalDateTime dateLock;

}

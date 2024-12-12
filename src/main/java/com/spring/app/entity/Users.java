package com.spring.app.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users extends BaseEntity {
    private String username;
    private String email;
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime lastSession;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(referencedColumnName = "id")
    private Roles role;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "id")
    private Accounts account;

    @Override
    public String toString() {
        String entity = String.format("""
            Users(
                id=%d,
                username=%s, 
                email=%s,
                password=%s,
                lastSession=%s,
                role=%s,
                dateLock=%s
            )     
        """, id, username, email, password, lastSession, role);
        return entity;
    }
}

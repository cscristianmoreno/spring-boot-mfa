package com.spring.app.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import com.spring.app.entity.Accounts;

import jakarta.transaction.Transactional;

@EnableJpaRepositories
public interface AccountRepository extends CrudRepository<Accounts, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Accounts a SET a.isAccountNonLocked = true, a.attemps = 0 WHERE a.id = :id")
    void updateIsAccountNonLocked(int id);

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE Accounts a
        SET 
            a.isAccountNonLocked = false,
            a.dateLock = :duration
        WHERE a.id = :id
    """)
    public void updateIsAccountLocked(int id, LocalDateTime duration);

    @Modifying
    @Transactional
    @Query("UPDATE Accounts a SET a.attemps = a.attemps + 1 WHERE a.id = :id")
    void updatePlusAttemps(int id);

    List<Accounts> findAll();
}

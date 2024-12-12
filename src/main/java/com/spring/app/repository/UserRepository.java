package com.spring.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import com.spring.app.entity.Users;

import jakarta.transaction.Transactional;

@EnableJpaRepositories
public interface UserRepository extends CrudRepository<Users, Integer> {
    Optional<Users> findById(int id);

    List<Users> findAll();

    Optional<Users> findByUsername(String username);

    @Modifying
    @Transactional
    @Query("DELETE FROM Users u WHERE u.username = :username")
    void deleteByUsername(String username);
}

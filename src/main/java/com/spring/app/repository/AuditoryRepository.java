package com.spring.app.repository;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import com.spring.app.entity.Auditory;

@EnableJpaRepositories
public interface AuditoryRepository extends CrudRepository<Auditory, Integer> {
    
}

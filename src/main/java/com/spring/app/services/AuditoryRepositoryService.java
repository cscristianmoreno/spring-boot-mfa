package com.spring.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.stereotype.Service;

import com.spring.app.entity.Auditory;
import com.spring.app.models.services.IAuditoryRepositoryService;
import com.spring.app.repository.AuditoryRepository;

@Service
public class AuditoryRepositoryService implements IAuditoryRepositoryService {

    @Autowired
    private AuditoryRepository auditoryRepository;

    @Override
    public Auditory save(Auditory entity) {
        return auditoryRepository.save(entity);
    }

    @Override
    public Auditory update(Auditory entity) {
        return auditoryRepository.save(entity);
    }

    @Override
    public Optional<Auditory> findById(int id) {
        return auditoryRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        auditoryRepository.deleteById(id);
    }
}

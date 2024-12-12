package com.spring.app.models.repository;

import java.util.Optional;

public interface IRepository<T> {
    T save(T entity);
    
    T update(T entity);

    Optional<T> findById(int id);

    void deleteById(int id);
}

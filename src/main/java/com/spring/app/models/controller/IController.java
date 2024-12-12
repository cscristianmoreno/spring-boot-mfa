package com.spring.app.models.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.app.dto.ResponseEntityDTO;

public interface IController<T> {
    @PostMapping("/save")
    ResponseEntity<ResponseEntityDTO> save(@RequestBody T entity);

    @PutMapping("/update")
    ResponseEntity<ResponseEntityDTO> update(@RequestBody T entity);

    @GetMapping("/{id}")
    ResponseEntity<ResponseEntityDTO> findById(@PathVariable int id);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseEntityDTO> deleteById(@PathVariable int id);
}

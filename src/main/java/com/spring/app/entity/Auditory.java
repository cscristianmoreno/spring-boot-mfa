package com.spring.app.entity;

import com.spring.app.enums.AuditoryEnum;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Auditory extends BaseEntity {
    private String className;
    private String methodName;
    private String exception;
}

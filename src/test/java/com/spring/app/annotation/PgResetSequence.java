package com.spring.app.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.test.context.jdbc.Sql;

import jakarta.transaction.Transactional;

@Retention(RetentionPolicy.RUNTIME)
@Sql(
    statements = "ALTER SEQUENCE IF EXISTS users_id_seq RESTART WITH 1"
)
@Transactional
public @interface PgResetSequence {
    
}

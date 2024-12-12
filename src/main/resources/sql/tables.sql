
-- DROP SCHEMA IF EXISTS public CASCADE;
-- CREATE SCHEMA public;

CREATE TABLE IF NOT EXISTS roles (
    id SERIAL PRIMARY KEY,
    name role_type UNIQUE,
    register TIMESTAMP(6) DEFAULT NOW()::timestamp(0)
);

CREATE TABLE IF NOT EXISTS accounts (
    id SERIAL PRIMARY KEY,
    is_account_non_expired BOOLEAN DEFAULT true,
    is_account_non_locked BOOLEAN DEFAULT true,
    is_credentials_non_expired BOOLEAN DEFAULT true,
    is_enabled BOOLEAN DEFAULT true,
    date_lock TIMESTAMP(6) DEFAULT NULL,
    attemps INTEGER DEFAULT 0,
    register TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP::timestamp(0)
);

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(32) NOT NULL,
    password VARCHAR(256) NOT NULL,
    email VARCHAR(56) NOT NULL UNIQUE,
    last_session TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP::timestamp(0),
    register TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP::timestamp(0), 
    role_id INTEGER NOT NULL,
    account_id INTEGER NOT NULL,

    CONSTRAINT fk_role_id FOREIGN KEY(role_id) REFERENCES roles(id),
    CONSTRAINT fk_account_id FOREIGN KEY(account_id) REFERENCES accounts(id)
);

CREATE TABLE IF NOT EXISTS auditory (
    id SERIAL PRIMARY KEY,
    class_name VARCHAR(72),
    method_name VARCHAR(72),
    exception TEXT DEFAULT '-',
    register TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP::timestamp(0)
);

CREATE TABLE IF NOT EXISTS user_totp (
    id SERIAL PRIMARY KEY,
    username VARCHAR(128) NOT NULL UNIQUE,
    secret_key VARCHAR(32) NOT NULL UNIQUE,
    scratch_codes INTEGER[] DEFAULT ('{}'),
    register TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP::timestamp(0)
);
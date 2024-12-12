package com.spring.app.exceptions;

public class ChangePasswordException extends RuntimeException {
    public ChangePasswordException(String message) {
        super(message);
    }
}

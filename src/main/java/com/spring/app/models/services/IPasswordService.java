package com.spring.app.models.services;

public interface IPasswordService {
    String encode(String rawPassword);
    
    boolean comparePassword(String rawPassword, String encodedPassword);

    String changePassword(String username, String lastPassword, String newPassword); 

}

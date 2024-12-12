package com.spring.app.dto;

public class ChangePasswordDTO {
    private String lastPassword;
    private String newPassword;

    public String getLastPassword() {
        return lastPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setLastPassword(String lastPassword) {
        this.lastPassword = lastPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

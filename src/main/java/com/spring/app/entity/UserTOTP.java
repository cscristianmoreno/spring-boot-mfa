package com.spring.app.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = "user_totp")
@Entity
@Getter
@Setter
@ToString
public class UserTOTP extends BaseEntity {
    private String username;
    private String secretKey;
    // private int validationCode;
    private List<Integer> scratchCodes;
}

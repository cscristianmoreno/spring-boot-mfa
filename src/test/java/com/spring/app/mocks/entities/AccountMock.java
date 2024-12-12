package com.spring.app.mocks.entities;

import com.spring.app.entity.Accounts;

public abstract class AccountMock {
    
    public static Accounts getAccount() {
        return new Accounts();
    }
}

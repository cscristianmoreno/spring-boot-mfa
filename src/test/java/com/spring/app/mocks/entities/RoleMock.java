package com.spring.app.mocks.entities;

import com.spring.app.entity.Roles;
import com.spring.app.enums.RolesEnum;

public abstract class RoleMock {
    
    public static Roles getRole() {
        Roles roles = new Roles();
        roles.setId(1);
        roles.setName(RolesEnum.ROLE_USER);
        return roles;
    }
}

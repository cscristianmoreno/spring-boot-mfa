package com.spring.app.entity;

import com.spring.app.enums.RolesEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = "name")
})
@Getter
@Setter
public class Roles extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private RolesEnum name;

    public RolesEnum getName() {
        return name;
    }

    public void setName(final RolesEnum name) {
        this.name = name;
    }
}

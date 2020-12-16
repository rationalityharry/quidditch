package ru.quidditch.webapp.data.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
public class AdminEntity extends UserEntity {

    public AdminEntity() {
    }

    public AdminEntity(UserEntity user) {
        super(user);
    }
}

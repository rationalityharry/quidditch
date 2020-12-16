package ru.quidditch.webapp.data.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "doctors")
@Entity
public class DoctorEntity extends UserEntity {

    public DoctorEntity() {
    }

    public DoctorEntity(UserEntity user) {
        super(user);
    }

}
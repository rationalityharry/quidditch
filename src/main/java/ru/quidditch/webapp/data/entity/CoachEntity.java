package ru.quidditch.webapp.data.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "coaches")
@Entity
public class CoachEntity extends UserEntity {

    public CoachEntity() {
    }

    public CoachEntity(UserEntity createdUser) {
        super(createdUser);
    }
}

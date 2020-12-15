package ru.quidditch.webapp.data.entity;

import javax.persistence.*;

@Table(name = "doctors")
@Entity
public class DoctorEntity {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private UserEntity user;

    public DoctorEntity() {
    }

    public DoctorEntity(UserEntity user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}

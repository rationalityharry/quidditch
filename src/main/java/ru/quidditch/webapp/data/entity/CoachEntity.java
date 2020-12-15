package ru.quidditch.webapp.data.entity;

import javax.persistence.*;

@Table(name = "coaches")
@Entity
public class CoachEntity {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private UserEntity user;

    @OneToOne
    private TeamEntity team;

    public CoachEntity() {
    }

    public CoachEntity(UserEntity user) {
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

    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }
}

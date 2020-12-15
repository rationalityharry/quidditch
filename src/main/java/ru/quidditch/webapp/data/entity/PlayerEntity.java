package ru.quidditch.webapp.data.entity;

import ru.quidditch.webapp.data.enums.PlayerPosition;

import javax.persistence.*;

@Table(name = "players")
@Entity
public class PlayerEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private int rate;

    @Column
    private PlayerPosition position;

    @OneToOne
    private UserEntity user;

    @Column
    private boolean captain;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamEntity teamEntity;

    public PlayerEntity() {
    }

    public PlayerEntity(UserEntity user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public void setPosition(PlayerPosition position) {
        this.position = position;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public boolean isCaptain() {
        return captain;
    }

    public void setCaptain(boolean captain) {
        this.captain = captain;
    }

    public TeamEntity getTeamEntity() {
        return teamEntity;
    }

    public void setTeamEntity(TeamEntity team) {
        this.teamEntity = team;
    }
}

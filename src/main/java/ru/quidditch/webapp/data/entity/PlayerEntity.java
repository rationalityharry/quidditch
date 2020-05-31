package ru.quidditch.webapp.data.entity;

import ru.quidditch.webapp.enums.PlayerPosition;

import javax.persistence.*;

@Table(name = "players")
@Entity
public class PlayerEntity {

    @Id
    @GeneratedValue
    private long playerId;

    @Column
    private int rate;

    @Column
    private PlayerPosition position;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @Column
    private boolean captain;

    public PlayerEntity() {
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
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
}

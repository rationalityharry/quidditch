package ru.quidditch.webapp.data.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "games")
@Entity
public class GameEntity {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private TeamEntity team1;

    @OneToOne
    private TeamEntity team2;

    @Column
    private int team1Score;

    @Column
    private int team2Score;

    @Column
    private String Location;

    @Column
    private Date date;

    public GameEntity() {
    }

    public int getResult() {
        return Integer.compare(team2Score, team1Score);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TeamEntity getTeam1() {
        return team1;
    }

    public void setTeam1(TeamEntity team1) {
        this.team1 = team1;
    }

    public TeamEntity getTeam2() {
        return team2;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTeam2(TeamEntity team2) {
        this.team2 = team2;
    }

    public int getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(int team1Score) {
        this.team1Score = team1Score;
    }

    public int getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(int team2Score) {
        this.team2Score = team2Score;
    }
}

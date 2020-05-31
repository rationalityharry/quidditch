package ru.quidditch.webapp.data.entity;

import javax.persistence.*;

@Table(name = "games")
@Entity
public class GameEntity {

    @Id
    @GeneratedValue
    private long gameId;

    @OneToOne
    private TeamEntity team1;

    @OneToOne
    private TeamEntity team2;

    @Column
    private int team1Score;

    @Column
    private int team2Score;

    public GameEntity() {
    }

    public int getResult() {
        return Integer.compare(team2Score, team1Score);
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
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

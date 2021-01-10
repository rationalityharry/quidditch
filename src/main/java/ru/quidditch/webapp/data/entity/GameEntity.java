package ru.quidditch.webapp.data.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Table(name = "games")
@Entity
public class GameEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column
    @NotNull
    private String team1;

    @Column
    @NotNull
    private String team2;

    @ManyToOne
    @JoinColumn(name = "operator_id")
    private OperatorEntity operator;

    @Column
    private Integer team1Score;

    @Column
    private Integer team2Score;

    @Column
    private String location;

    @Column
    private Date date;

    @Column
    private Date time;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public OperatorEntity getOperator() {
        return operator;
    }

    public void setOperator(OperatorEntity operator) {
        this.operator = operator;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(@NotNull String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(@NotNull String team2) {
        this.team2 = team2;
    }
}

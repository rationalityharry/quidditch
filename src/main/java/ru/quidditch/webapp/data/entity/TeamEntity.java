package ru.quidditch.webapp.data.entity;

import ru.quidditch.webapp.enums.Faculty;

import javax.persistence.*;
import java.util.List;

@Table(name = "teams")
@Entity
public class TeamEntity {

    @Id
    @GeneratedValue
    @Column
    private long id;

    @OneToMany(mappedBy = "playerId", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PlayerEntity> players;

    @Column
    private Faculty faculty;

    @OneToOne
    private UserEntity coach;

    @OneToOne
    private UserEntity doctor;

    @OneToMany(mappedBy = "gameId", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<GameEntity> games;


    public TeamEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<PlayerEntity> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerEntity> players) {
        this.players = players;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public UserEntity getCoach() {
        return coach;
    }

    public void setCoach(UserEntity coach) {
        this.coach = coach;
    }

    public UserEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(UserEntity doctor) {
        this.doctor = doctor;
    }

    public List<GameEntity> getGames() {
        return games;
    }

    public void setGames(List<GameEntity> games) {
        this.games = games;
    }

}

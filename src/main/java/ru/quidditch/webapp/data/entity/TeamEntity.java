package ru.quidditch.webapp.data.entity;

import ru.quidditch.webapp.data.enums.Faculty;

import javax.persistence.*;
import java.util.List;

@Table(name = "teams")
@Entity
public class TeamEntity {

    @Id
    @GeneratedValue
    @Column
    private long id;


    @OneToMany(mappedBy = "teamEntity")
    private List<PlayerEntity> players;

    @Column
    private Faculty faculty;

    @OneToMany(mappedBy = "team")
    private List<NewsEntity> news;

    @OneToOne
    private CoachEntity coach;


    /*@OneToMany(mappedBy = "team", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<GameEntity> games;*/


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

    public CoachEntity getCoach() {
        return coach;
    }

    public void setCoach(CoachEntity coach) {
        this.coach = coach;
    }

   /* public List<GameEntity> getGames() {
        return games;
    }

    public void setGames(List<GameEntity> games) {
        this.games = games;
    }*/

    public List<NewsEntity> getNews() {
        return news;
    }

    public void setNews(List<NewsEntity> news) {
        this.news = news;
    }
}

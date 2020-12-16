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


    @OneToMany(mappedBy = "team")
    private List<UserEntity> users;

    @Column
    private Faculty faculty;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
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

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
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

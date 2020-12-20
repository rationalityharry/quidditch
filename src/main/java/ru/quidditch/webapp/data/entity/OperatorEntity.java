package ru.quidditch.webapp.data.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "operators")
public class OperatorEntity extends UserEntity {

    @Column
    @OneToMany(mappedBy = "operator", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<NewsEntity> news;

    @Column
    @OneToMany(mappedBy = "operator")
    private List<GameEntity> games;

    public OperatorEntity() {
    }

    public List<GameEntity> getGames() {
        return games;
    }

    public void setGames(List<GameEntity> games) {
        this.games = games;
    }

    public OperatorEntity(UserEntity createdUser) {
    super(createdUser);
    }

    public List<NewsEntity> getNews() {
        return news;
    }

    public void setNews(List<NewsEntity> news) {
        this.news = news;
    }
}

package ru.quidditch.webapp.data.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "operators")
public class OperatorEntity {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private UserEntity user;

    @Column
    @OneToMany (mappedBy = "operator", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<NewsEntity> news;

    public OperatorEntity() {
    }

    public OperatorEntity(UserEntity user) {
        this.user = user;
    }



    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<NewsEntity> getNews() {
        return news;
    }

    public void setNews(List<NewsEntity> news) {
        this.news = news;
    }
}

package ru.sofitlabs.webapp.data.animebase;

import ru.sofitlabs.webapp.data.user.UserEntity;

import javax.persistence.*;

@Table(name = "comments")
@Entity
public class CommentsEntity {

    @Id
    @GeneratedValue
    private long commentId;

    @Column
    private int rate;

    @Column
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "anime_id", referencedColumnName = "id")
    private AnimeEntity anime;

    public CommentsEntity() {
    }

    public String getBody() {
        return body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(final long commentId) {
        this.commentId = commentId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(final int rate) {
        this.rate = rate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(final UserEntity user) {
        this.user = user;
    }

    public AnimeEntity getAnime() {
        return anime;
    }

    public void setAnime(final AnimeEntity anime) {
        this.anime = anime;
    }
}

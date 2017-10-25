package ru.sofitlabs.firstwebapp.data.animebase;

import javax.persistence.*;
import java.util.List;

@Table(name = "animebase")
@Entity
public class AnimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "anime_id")
    private long id;

    @OneToMany(mappedBy = "anime", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CommentsEntity> titleComments;

    @Column
    private String name;

    @Column
    private String genre;

    @Column
    private String author;

    @Column
    private String description;

    @Column
    private String animeImagePath;

    public AnimeEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(final String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getAnimeImagePath() {
        return animeImagePath;
    }

    public void setAnimeImagePath(final String animeImagePath) {
        this.animeImagePath = animeImagePath;
    }
}

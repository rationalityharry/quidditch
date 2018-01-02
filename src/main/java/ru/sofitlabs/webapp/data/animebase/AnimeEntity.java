package ru.sofitlabs.webapp.data.animebase;

import javax.persistence.*;
import java.util.List;

@Table(name = "animebase")
@Entity
public class AnimeEntity {

    @Id
    @GeneratedValue
    @Column
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

    @OneToOne
    @JoinColumn(name = "animeImage_id")
    private ImageEntity animeKey;

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

    public ImageEntity getAnimeImage() {
        return animeKey;
    }

    public void setAnimeImage(final ImageEntity animeImage) {
        this.animeKey = animeImage;
    }
}

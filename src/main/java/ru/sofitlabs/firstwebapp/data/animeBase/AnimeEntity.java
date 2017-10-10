package ru.sofitlabs.firstwebapp.data.animeBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "animebase")
@Entity
public class AnimeEntity {
    @Id
    @GeneratedValue
    private long id;
    public AnimeEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    private String name;
    private String genre;
    private String author;
    private String description;
    private String animeLink;

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

    public String getAnimeLink() {
        return animeLink;
    }

    public void setAnimeLink(final String animeLink) {
        this.animeLink = animeLink;
    }
}

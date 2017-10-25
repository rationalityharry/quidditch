package ru.sofitlabs.firstwebapp.data.animebase;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Table(name = "imageBase")
@Entity
public class ImageEntity {
    @Column
    @Value("${image.source}")
    private String imagePath;

    @Column
    private String imageName;

    @Column
    private long animeId;

    @Id
    @GeneratedValue
    @Column
    private long id;

    public ImageEntity() {
    }

    public long getAnimeId() {
        return animeId;
    }

    public void setAnimeId(final long animeId) {
        this.animeId = animeId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(final String imagePath) {
        this.imagePath = imagePath;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(final String imageName) {
        this.imageName = imageName;
    }
}

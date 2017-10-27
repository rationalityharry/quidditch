package ru.sofitlabs.firstwebapp.data.animebase;

import javax.persistence.*;
import java.nio.file.Path;

@Table(name = "imageBase")
@Entity
public class ImageEntity {

    @Column
    private String imagePath;

    @Column
    private String imageName;


    @OneToOne
    @JoinColumn(name = "animeImage_id")
    private AnimeEntity animeKey;

    @Id
    @GeneratedValue
    @Column
    private long id;

    public ImageEntity() {
    }

    public AnimeEntity getAnimeId() {
        return animeKey;
    }

    public void setAnimeId(final AnimeEntity anime) {
        this.animeKey = anime;
    }

    public String  getImagePath() {
        return imagePath;
    }

    public void setImagePath(final String  imagePath) {
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

package ru.sofitlabs.firstwebapp.data.animebase;

import javax.persistence.*;

@Table(name = "imageBase")
@Entity
public class ImageEntity {

    @Column
    private String imagePath;

    @Column
    private String imageName;

    @Transient
    @OneToOne(mappedBy = "animeKey")
    private AnimeEntity animeImage;

    @Id
    @GeneratedValue
    @Column
    private long id;

    public ImageEntity() {
    }

    public AnimeEntity getAnimeId() {
        return animeImage;
    }

    public void setAnimeId(final AnimeEntity anime) {
        this.animeImage = anime;
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

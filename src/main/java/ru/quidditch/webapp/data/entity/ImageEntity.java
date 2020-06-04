package ru.quidditch.webapp.data.entity;

import javax.persistence.*;

@Table(name = "imageBase")
@Entity
public class ImageEntity {

    @Column
    private String imagePath;

    @Column
    private String imageName;

    @Transient
    @OneToOne(mappedBy = "userKey")
    private UserEntity avatarImage;

    @Id
    @GeneratedValue
    @Column
    private long id;

    public ImageEntity() {
    }

    public UserEntity getUserId() {
        return avatarImage;
    }

    public void setAnimeId(final UserEntity anime) {
        this.avatarImage = anime;
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

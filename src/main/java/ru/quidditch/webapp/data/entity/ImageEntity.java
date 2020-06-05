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
    private UserEntity user;

    @Id
    @GeneratedValue
    @Column
    private long avatarImageId;

    public ImageEntity() {
    }

    public UserEntity getUserId() {
        return user;
    }

    public void setAnimeId(final UserEntity anime) {
        this.user = anime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(final String imagePath) {
        this.imagePath = imagePath;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity avatarImage) {
        this.user = avatarImage;
    }

    public long getAvatarImageid() {
        return avatarImageId;
    }

    public void setAvatarImageid(long avatarImageid) {
        this.avatarImageId = avatarImageid;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(final String imageName) {
        this.imageName = imageName;
    }
}

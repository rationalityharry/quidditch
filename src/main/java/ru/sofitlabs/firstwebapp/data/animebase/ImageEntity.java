package ru.sofitlabs.firstwebapp.data.animebase;

import javax.persistence.*;

@Table(name = "imageBase")
@Entity
public class ImageEntity {
    @Column
    private String imageSource;

    @Id
    @GeneratedValue
    private long id;

    public ImageEntity() {
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(final String imageSource) {
        this.imageSource = imageSource;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }
}

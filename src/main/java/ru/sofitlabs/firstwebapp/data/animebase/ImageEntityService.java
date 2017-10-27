package ru.sofitlabs.firstwebapp.data.animebase;

public interface ImageEntityService {

    ImageEntity add(ImageEntity image);

    String getRootLocation();

    ImageEntity getOneByAnime(AnimeEntity anime);
}

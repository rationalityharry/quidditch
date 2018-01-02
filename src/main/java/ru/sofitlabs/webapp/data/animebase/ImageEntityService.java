package ru.sofitlabs.webapp.data.animebase;

public interface ImageEntityService {

    ImageEntity add(ImageEntity image);

    String getRootLocation();

    ImageEntity getOneByAnime(AnimeEntity anime);

    ImageEntity getById(long imageId);
}

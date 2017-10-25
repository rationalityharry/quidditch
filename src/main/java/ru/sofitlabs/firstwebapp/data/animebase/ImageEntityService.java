package ru.sofitlabs.firstwebapp.data.animebase;

import java.io.OutputStream;

public interface ImageEntityService {

    ImageEntity add(ImageEntity image);

    String getImagePath(long imageId);
}

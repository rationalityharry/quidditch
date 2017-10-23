package ru.sofitlabs.firstwebapp.data.animebase;

import java.io.OutputStream;

public interface ImageEntityService {

    void add();

    OutputStream getImageById();

    String getImagePath();
}

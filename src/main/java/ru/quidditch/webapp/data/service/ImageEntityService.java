package ru.quidditch.webapp.data.service;

import ru.quidditch.webapp.data.entity.ImageEntity;
import ru.quidditch.webapp.data.entity.UserEntity;

public interface ImageEntityService {

    ImageEntity add(ImageEntity image);

    String getRootLocation();

    ImageEntity getOneByUser(UserEntity anime);

    ImageEntity getById(long imageId);
}

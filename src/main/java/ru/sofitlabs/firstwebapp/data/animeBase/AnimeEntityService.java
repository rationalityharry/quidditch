package ru.sofitlabs.firstwebapp.data.animeBase;

import java.util.List;

public interface AnimeEntityService {
    void add(AnimeEntity animeEntity);

    List<AnimeEntity> getAll();

    List<AnimeEntity> getAllbyName(String name);
}

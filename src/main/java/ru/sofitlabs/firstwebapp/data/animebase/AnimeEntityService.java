package ru.sofitlabs.firstwebapp.data.animebase;

import java.util.List;

public interface AnimeEntityService {

    AnimeEntity add(AnimeEntity animeEntity);

    List<AnimeEntity> getAll();

    List<AnimeEntity> getAllbyName(String name);
}

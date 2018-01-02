package ru.sofitlabs.webapp.data.animebase;

import java.util.List;

public interface AnimeEntityService {

    AnimeEntity add(AnimeEntity animeEntity);

    List<AnimeEntity> getAll();

    AnimeEntity findOneById(long id);

    long getAnimeId(String name);
}

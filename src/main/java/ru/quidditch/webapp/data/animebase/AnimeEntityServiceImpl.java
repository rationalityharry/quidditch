package ru.quidditch.webapp.data.animebase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnimeEntityServiceImpl implements AnimeEntityService {
    @Autowired
    public AnimeRepository animeRepository;

    @Override
    public AnimeEntity add(final AnimeEntity animeEntity) {
        return animeRepository.save(animeEntity);
    }

    @Override
    public List<AnimeEntity> getAll() {
        return animeRepository.findAll();
    }

    @Override
    public AnimeEntity findOneById(final long id) {
        return animeRepository.findOne(id);
    }

    public long getAnimeId(final String name) {
        return animeRepository.findOneByName(name);
    }

}

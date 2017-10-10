package ru.sofitlabs.firstwebapp.data.animebase;

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
    public List<AnimeEntity> getAllbyName(final String name) {
        return animeRepository.findAllByName(name);
    }
}

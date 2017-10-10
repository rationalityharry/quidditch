package ru.sofitlabs.firstwebapp.data.animeBase;

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
    public void add(final AnimeEntity animeEntity) {
        animeRepository.save(animeEntity);
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

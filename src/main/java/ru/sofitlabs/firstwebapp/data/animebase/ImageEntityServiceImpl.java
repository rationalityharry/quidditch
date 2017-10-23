package ru.sofitlabs.firstwebapp.data.animebase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;

@Service
@Transactional
public class ImageEntityServiceImpl implements ImageEntityService {

    @Autowired
    AnimeRepository animeRepository;

    @Override
    public void add() {

    }

    @Override
    public OutputStream getImageById() {
        return null;
    }

    @Override
    public String getImagePath() {
        return null;
    }
}

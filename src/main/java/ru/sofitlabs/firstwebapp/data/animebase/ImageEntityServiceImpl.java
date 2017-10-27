package ru.sofitlabs.firstwebapp.data.animebase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImageEntityServiceImpl implements ImageEntityService {

    @Value("${image.source}")
    private String rootLocation;

    @Autowired
    AnimeRepository animeRepository;

    @Autowired
    ImageRepository imageRepository;

    @Override
    public String getRootLocation() {
        return rootLocation;
    }

    @Override
    public ImageEntity getOneByAnime(final AnimeEntity anime) {
       return imageRepository.findOne(animeRepository.findOne(anime.getId()).getAnimeImage().getId());
    }

    @Override
    public ImageEntity add(ImageEntity image) {
        return imageRepository.save(image);
    }


}

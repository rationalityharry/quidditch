package ru.sofitlabs.firstwebapp.data.animebase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImageEntityServiceImpl implements ImageEntityService {

    @Autowired
    AnimeRepository animeRepository;

    @Autowired
    ImageRepository imageRepository;


    public ImageEntity add(ImageEntity image) {
        return imageRepository.save(image);
    }

    public String getImagePath(long imageId) {
        return imageRepository.findOne(imageId).getImagePath();
    }
}

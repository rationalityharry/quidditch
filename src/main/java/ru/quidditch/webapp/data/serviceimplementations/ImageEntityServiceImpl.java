package ru.quidditch.webapp.data.serviceimplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.quidditch.webapp.data.entity.ImageEntity;
import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.repository.ImageRepository;
import ru.quidditch.webapp.data.repository.UserRepository;
import ru.quidditch.webapp.data.service.ImageEntityService;

@Service
@Transactional
public class ImageEntityServiceImpl implements ImageEntityService {

    @Value("${image.source}")
    private String rootLocation;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ImageRepository imageRepository;

    @Override
    public String getRootLocation() {
        return rootLocation;
    }

    @Override
    public ImageEntity getOneByUser(final UserEntity user) {
        return imageRepository.findOne(userRepository.findOne(user.getId()).getUserKey().getId());
    }

    @Override
    public ImageEntity getById(final long imageId) {
        return imageRepository.findOne(imageId);
    }

    @Override
    public ImageEntity add(ImageEntity image) {
        return imageRepository.save(image);
    }


}

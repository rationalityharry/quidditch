package ru.sofitlabs.webapp.data.animebase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sofitlabs.webapp.data.user.UserEntity;

import java.util.List;

@Service
@Transactional
public class CommentsEntityServiceImpl implements CommentsEntityService {

    @Autowired
    public CommentsRepository commentsRepository;

    @Override
    public CommentsEntity add(final CommentsEntity comment) {
        return commentsRepository.save(comment);
    }

    @Override
    public List<CommentsEntity> getAll() {
        return commentsRepository.findAll();
    }

    @Override
    public List<CommentsEntity> getAllByUser(final UserEntity user) {
        return commentsRepository.findAllByUser(user);
    }

    @Override
    public List<CommentsEntity> getAllByAnime(final AnimeEntity anime) {
        return commentsRepository.findAllByAnime(anime);
    }

}

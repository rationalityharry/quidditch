package ru.sofitlabs.firstwebapp.data.animeBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentsEntityServiceImpl implements CommentsEntityService {

    @Autowired
    public CommentsRepository commentsRepository;

    @Override
    public void add(final CommentsEntity comment) {
        commentsRepository.save(comment);
    }

    @Override
    public List<CommentsEntity> getAll() {
        return commentsRepository.findAll();
    }

    @Override
    public List<CommentsEntity> getAllByUser(final String user) {
        return commentsRepository.findAllByUser(user);
    }
}

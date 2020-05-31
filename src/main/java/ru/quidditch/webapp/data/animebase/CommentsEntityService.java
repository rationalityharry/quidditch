package ru.quidditch.webapp.data.animebase;

import ru.quidditch.webapp.data.user.UserEntity;

import java.util.List;

public interface CommentsEntityService {
    CommentsEntity add(CommentsEntity comment);

    List<CommentsEntity> getAll();

    List<CommentsEntity> getAllByUser(UserEntity user);

    List<CommentsEntity> getAllByAnime(AnimeEntity anime);
}

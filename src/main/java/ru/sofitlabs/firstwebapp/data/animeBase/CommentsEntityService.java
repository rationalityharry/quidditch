package ru.sofitlabs.firstwebapp.data.animeBase;

import java.util.List;

public interface CommentsEntityService {
    void add(CommentsEntity comment);
    List<CommentsEntity> getAll();
    List<CommentsEntity> getAllByUser(String user);
}

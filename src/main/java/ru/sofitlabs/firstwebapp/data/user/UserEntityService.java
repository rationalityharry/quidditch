package ru.sofitlabs.firstwebapp.data.user;

import java.util.List;

public interface UserEntityService {
    void add(UserEntity userEntity);

    List<UserEntity> getAll();

    List<UserEntity> getAllByLogin(String login);

    List<UserEntity> getAllbyId(long id);
}

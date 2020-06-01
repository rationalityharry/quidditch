package ru.quidditch.webapp.data.service;

import ru.quidditch.webapp.data.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity add(UserEntity userEntity);

    List<UserEntity> getAll();

    List<UserEntity> getAllByLogin(String login);

    UserEntity getOneByLogin(String login);

    UserEntity getById(Long id);

    List<UserEntity> getAllDisabled();
}

package ru.quidditch.webapp.data.user;

import java.util.List;

public interface UserEntityService {
    UserEntity add(UserEntity userEntity);

    List<UserEntity> getAll();

    List<UserEntity> getAllByLogin(String login);

    List<UserEntity> getAllbyId(long id);

    String getPasswordbyLogin(String login);

    UserEntity getOneByLogin(String login);
}

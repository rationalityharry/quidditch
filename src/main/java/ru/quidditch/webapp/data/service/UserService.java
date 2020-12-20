package ru.quidditch.webapp.data.service;

import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.enums.Faculty;

import java.util.List;

public interface UserService {
    UserEntity save(UserEntity userEntity);

    List<UserEntity> getAll();

    List<UserEntity> getAllAdmins();

    List<UserEntity> getAllByLogin(String login);

    UserEntity getOneByLogin(String login);

    UserEntity getById(Long id);

    List<UserEntity> getAllDisabled();

    List<UserEntity> getAllByFaculty(Faculty faculty);
}

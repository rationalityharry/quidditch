package ru.quidditch.webapp.data.service;

import ru.quidditch.webapp.data.entity.PlayerEntity;
import ru.quidditch.webapp.data.enums.Faculty;

import java.util.List;

import java.util.List;

public interface PlayerService {

    PlayerEntity save(PlayerEntity playerEntity);

    PlayerEntity findPlayerById(Long id);

    List<PlayerEntity> getAll();


    List<PlayerEntity> findPlayersByFaculty(Faculty faculty);
}

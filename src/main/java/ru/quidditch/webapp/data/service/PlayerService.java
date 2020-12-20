package ru.quidditch.webapp.data.service;

import ru.quidditch.webapp.data.entity.PlayerEntity;

import java.util.List;

public interface PlayerService {

    PlayerEntity save(PlayerEntity playerEntity);

    List<PlayerEntity> getAll();


}

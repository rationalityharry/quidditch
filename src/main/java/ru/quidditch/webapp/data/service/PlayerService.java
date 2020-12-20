package ru.quidditch.webapp.data.service;

import ru.quidditch.webapp.data.entity.PlayerEntity;

public interface PlayerService {

    PlayerEntity save(PlayerEntity playerEntity);

    PlayerEntity findPlayerById(Long id);

}

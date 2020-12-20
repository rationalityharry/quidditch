package ru.quidditch.webapp.data.service;

import ru.quidditch.webapp.data.entity.GameEntity;

import java.util.Date;
import java.util.List;

public interface GameService {
    GameEntity save(GameEntity gameEntity);

    List<GameEntity> getGames(Date date);

    List<GameEntity> getEndedGames(Date date);

}

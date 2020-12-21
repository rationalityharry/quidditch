package ru.quidditch.webapp.data.serviceimplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.quidditch.webapp.data.entity.GameEntity;
import ru.quidditch.webapp.data.repository.GameRepository;
import ru.quidditch.webapp.data.service.GameService;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class GameEntityServiceImpl implements GameService {

    @Autowired
    public GameRepository gameRepository;


    @Override
    public GameEntity save(GameEntity gameEntity) {
        return gameRepository.save(gameEntity);
    }

    @Override
    public GameEntity getGameById(Long id) {
        return gameRepository.findOne(id);
    }

    @Override
    public List<GameEntity> getGames(Date date) {
        return gameRepository.findAllByDateAfter(date);
    }

    @Override
    public List<GameEntity> getEndedGames(Date date) {
        return gameRepository.findAllByDateBefore(date);
    }
}

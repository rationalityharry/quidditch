package ru.quidditch.webapp.data.serviceimplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.quidditch.webapp.data.entity.PlayerEntity;
import ru.quidditch.webapp.data.repository.PlayerRepository;
import ru.quidditch.webapp.data.service.PlayerService;

import java.util.List;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    public PlayerRepository playerRepository;

    @Override
    public PlayerEntity save(PlayerEntity playerEntity) {
        return playerRepository.save(playerEntity);
    }

    @Override
    public List<PlayerEntity> getAll() {
        return playerRepository.findAllByDisabledIsFalse();
    }

    @Override
    public PlayerEntity findPlayerById(Long id) {
        return playerRepository.findOne(id);
    }
}

package ru.quidditch.webapp.data.serviceimplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.quidditch.webapp.data.entity.PlayerEntity;
import ru.quidditch.webapp.data.enums.Faculty;
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

    @Override
    public List<PlayerEntity> findPlayersByRating() {
        return playerRepository.findAll(new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 10;
            }

            @Override
            public int getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        }).getContent();
    }

    @Override
    public List<PlayerEntity> findPlayersByFaculty(Faculty faculty) {
        return playerRepository.findAllByFaculty(faculty);
    }
}

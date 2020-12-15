package ru.quidditch.webapp.data.serviceimplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.quidditch.webapp.data.entity.CoachEntity;
import ru.quidditch.webapp.data.repository.CoachRepository;
import ru.quidditch.webapp.data.service.CoachService;

import javax.transaction.Transactional;

@Service
@Transactional

public class CoachServiceImpl implements CoachService {

    @Autowired
    private CoachRepository repository;

    @Override
    public CoachEntity save(CoachEntity coachEntity) {
        return repository.save(coachEntity);
    }
}

package ru.quidditch.webapp.data.serviceimplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.quidditch.webapp.data.entity.TrainingEntity;
import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.repository.TrainingRepository;
import ru.quidditch.webapp.data.service.TrainingService;

import javax.transaction.Transactional;

@Service
@Transactional

public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingRepository repository;

    @Override
    public TrainingEntity getTrainingsByCoach(UserEntity coach) {
        return repository.findByFaculty(coach.getFaculty());
    }

    @Override
    public TrainingEntity add(TrainingEntity trainingEntity) {
        return repository.save(trainingEntity);
    }
}

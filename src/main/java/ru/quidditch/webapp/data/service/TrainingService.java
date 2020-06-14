package ru.quidditch.webapp.data.service;

import ru.quidditch.webapp.data.entity.TrainingEntity;
import ru.quidditch.webapp.data.entity.UserEntity;

public interface TrainingService {

    TrainingEntity getTrainingsByCoach(UserEntity coach);


    TrainingEntity add(TrainingEntity trainingEntity);
}

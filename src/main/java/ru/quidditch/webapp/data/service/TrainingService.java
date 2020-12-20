package ru.quidditch.webapp.data.service;

import ru.quidditch.webapp.data.entity.TrainingEntity;
import ru.quidditch.webapp.data.entity.UserEntity;

public interface TrainingService {

    TrainingEntity getTrainingsByUserFaculty(UserEntity coach);


    TrainingEntity save(TrainingEntity trainingEntity);
}

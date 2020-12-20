package ru.quidditch.webapp.data.service;

import ru.quidditch.webapp.data.entity.MedicalExaminationEntity;
import ru.quidditch.webapp.data.entity.PlayerEntity;

import java.util.List;

public interface MedicalExaminationService {

    MedicalExaminationEntity save(MedicalExaminationEntity medicalExaminationEntity);

    List<MedicalExaminationEntity> getExaminationsForPlayer(PlayerEntity playerEntity);
}

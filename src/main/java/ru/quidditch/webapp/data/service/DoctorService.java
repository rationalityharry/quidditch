package ru.quidditch.webapp.data.service;

import ru.quidditch.webapp.data.entity.DoctorEntity;
import ru.quidditch.webapp.data.entity.MedicalExaminationEntity;
import ru.quidditch.webapp.data.entity.PlayerEntity;

import java.util.List;

public interface DoctorService {
    DoctorEntity save(DoctorEntity doctorEntity);


    MedicalExaminationEntity createExamination(PlayerEntity player);
}

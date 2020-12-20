package ru.quidditch.webapp.data.serviceimplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.quidditch.webapp.data.entity.DoctorEntity;
import ru.quidditch.webapp.data.entity.MedicalExaminationEntity;
import ru.quidditch.webapp.data.entity.PlayerEntity;
import ru.quidditch.webapp.data.repository.DoctorRepository;
import ru.quidditch.webapp.data.repository.PlayerRepository;
import ru.quidditch.webapp.data.service.DoctorService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional

public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository repository;

    @Autowired
    public PlayerRepository playerRepository;

    @Override
    public DoctorEntity save(DoctorEntity doctorEntity) {
        return repository.save(doctorEntity);
    }

    public MedicalExaminationEntity createExamination(PlayerEntity player){
        MedicalExaminationEntity examinationEntity = new MedicalExaminationEntity();
        examinationEntity.setPlayer(player);
        return examinationEntity;
    }
}

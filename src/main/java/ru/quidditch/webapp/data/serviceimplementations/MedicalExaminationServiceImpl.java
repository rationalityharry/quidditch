package ru.quidditch.webapp.data.serviceimplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.quidditch.webapp.data.entity.MedicalExaminationEntity;
import ru.quidditch.webapp.data.entity.PlayerEntity;
import ru.quidditch.webapp.data.repository.MedicalExaminationRepository;
import ru.quidditch.webapp.data.repository.PlayerRepository;
import ru.quidditch.webapp.data.service.MedicalExaminationService;

import java.util.List;

@Service
@Transactional
public class MedicalExaminationServiceImpl implements MedicalExaminationService {

    @Autowired
    MedicalExaminationRepository examinationRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public MedicalExaminationEntity save(MedicalExaminationEntity medicalExaminationEntity) {
        return examinationRepository.save(medicalExaminationEntity);
    }

    @Override
    public List<MedicalExaminationEntity> getExaminationsForPlayer(PlayerEntity playerEntity) {
        return examinationRepository.findAllByPlayerOrderByDateDesc(playerEntity);
    }
}

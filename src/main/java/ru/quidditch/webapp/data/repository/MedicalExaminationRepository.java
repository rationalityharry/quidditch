package ru.quidditch.webapp.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.quidditch.webapp.data.entity.MedicalExaminationEntity;
import ru.quidditch.webapp.data.entity.PlayerEntity;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface MedicalExaminationRepository extends JpaRepository<MedicalExaminationEntity, Long> {

    List<MedicalExaminationEntity> findAllByPlayerOrderByDateDesc(PlayerEntity playerEntity);

}

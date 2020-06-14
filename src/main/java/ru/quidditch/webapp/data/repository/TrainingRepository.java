package ru.quidditch.webapp.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.quidditch.webapp.data.entity.TrainingEntity;
import ru.quidditch.webapp.data.enums.Faculty;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface TrainingRepository extends JpaRepository<TrainingEntity, Long> {

    TrainingEntity findByFaculty(Faculty faculty);

}

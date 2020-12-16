package ru.quidditch.webapp.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.quidditch.webapp.data.entity.TeamEntity;
import ru.quidditch.webapp.data.enums.Faculty;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

    TeamEntity findByFaculty(Faculty faculty);

}

package ru.quidditch.webapp.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.quidditch.webapp.data.entity.GameEntity;

import java.util.Date;
import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface GameRepository extends JpaRepository<GameEntity, Long> {

    List<GameEntity> findAllByDateAfter(Date date);
    List<GameEntity> findAllByDateBefore(Date date);

}

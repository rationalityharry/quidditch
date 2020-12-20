package ru.quidditch.webapp.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.quidditch.webapp.data.entity.PlayerEntity;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

    PlayerEntity findById(Long id);

    List<PlayerEntity> findAllByDisabledIsFalse();

}

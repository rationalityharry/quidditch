package ru.quidditch.webapp.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.quidditch.webapp.data.entity.UserEntity;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAllByLogin(String login);

    List<UserEntity> findAllByDisabledIsFalse();

    List<UserEntity> findAllByEnabledIsTrue();

    List<UserEntity> findAllByDisabledIsTrue();

    List<UserEntity> findAllByEnabledIsFalse();

    UserEntity findById(Long id);

}

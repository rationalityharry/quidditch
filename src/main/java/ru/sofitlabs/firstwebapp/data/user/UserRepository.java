package ru.sofitlabs.firstwebapp.data.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAllByLogin(String login);

    List<UserEntity> findAllById(long id);




}

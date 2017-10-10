package ru.sofitlabs.firstwebapp.data.animeBase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional (propagation = Propagation.MANDATORY)
public interface CommentsRepository extends JpaRepository<CommentsEntity, Long>{
    List<CommentsEntity> findAllByUser(String user);
}

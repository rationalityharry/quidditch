package ru.sofitlabs.webapp.data.animebase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sofitlabs.webapp.data.user.UserEntity;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface CommentsRepository extends JpaRepository<CommentsEntity, Long> {

    List<CommentsEntity> findAllByUser(UserEntity user);

    List<CommentsEntity> findAllByAnime(AnimeEntity anime);

}

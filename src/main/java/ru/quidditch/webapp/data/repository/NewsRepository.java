package ru.quidditch.webapp.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.quidditch.webapp.data.entity.NewsEntity;
import ru.quidditch.webapp.data.entity.TeamEntity;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {

    List<NewsEntity> findAllByTeamOrderByDateDesc(TeamEntity teamEntity);

}

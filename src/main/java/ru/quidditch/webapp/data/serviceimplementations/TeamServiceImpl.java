package ru.quidditch.webapp.data.serviceimplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.quidditch.webapp.data.entity.TeamEntity;
import ru.quidditch.webapp.data.enums.Faculty;
import ru.quidditch.webapp.data.repository.TeamRepository;
import ru.quidditch.webapp.data.service.TeamService;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    @Autowired
    public TeamRepository teamRepository;


    @Override
    public TeamEntity getTeamByFaculty(Faculty faculty) {

        return teamRepository.findByFaculty(faculty);

    }

    @Override
    public TeamEntity save(TeamEntity team) {
        return teamRepository.save(team);
    }
}

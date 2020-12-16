package ru.quidditch.webapp.data.service;

import ru.quidditch.webapp.data.entity.TeamEntity;
import ru.quidditch.webapp.data.enums.Faculty;

public interface TeamService {

    TeamEntity getTeamByFaculty(Faculty faculty);

    TeamEntity save(TeamEntity team);
}

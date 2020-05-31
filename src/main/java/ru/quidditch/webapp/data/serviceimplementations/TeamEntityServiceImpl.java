package ru.quidditch.webapp.data.serviceimplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.quidditch.webapp.data.repository.TeamRepository;
import ru.quidditch.webapp.data.service.TeamEntityService;

@Service
@Transactional
public class TeamEntityServiceImpl implements TeamEntityService {

    @Autowired
    public TeamRepository animeRepository;


}

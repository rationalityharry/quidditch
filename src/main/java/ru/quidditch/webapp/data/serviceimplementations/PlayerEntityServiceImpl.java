package ru.quidditch.webapp.data.serviceimplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.quidditch.webapp.data.repository.PlayerRepository;
import ru.quidditch.webapp.data.service.PlayerEntityService;

@Service
@Transactional
public class PlayerEntityServiceImpl implements PlayerEntityService {

    @Autowired
    public PlayerRepository commentsRepository;


}

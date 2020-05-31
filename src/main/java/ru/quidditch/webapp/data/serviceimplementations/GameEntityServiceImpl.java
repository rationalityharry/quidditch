package ru.quidditch.webapp.data.serviceimplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.quidditch.webapp.data.repository.GameRepository;
import ru.quidditch.webapp.data.service.GameEntityService;

@Service
@Transactional
public class GameEntityServiceImpl implements GameEntityService {

    @Autowired
    public GameRepository commentsRepository;


}

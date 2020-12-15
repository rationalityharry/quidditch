package ru.quidditch.webapp.data.serviceimplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.quidditch.webapp.data.entity.OperatorEntity;
import ru.quidditch.webapp.data.repository.OperatorRepository;
import ru.quidditch.webapp.data.service.OperatorService;

import javax.transaction.Transactional;

@Service
@Transactional

public class OperatorServiceImpl implements OperatorService {

    @Autowired
    private OperatorRepository repository;

    @Override
    public OperatorEntity save(OperatorEntity operatorEntity) {
        return repository.save(operatorEntity);
    }
}

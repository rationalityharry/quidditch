package ru.sofitlabs.firstwebapp.data.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TestEntityServiceImpl implements TestEntityService {

    @Autowired
    private TestRepository repository;

    @Override
    public void add(TestEntity testEntity) {
        repository.save(testEntity);
    }

    @Override
    public List<TestEntity> getAll() {
        return repository.findAll();
    }

}

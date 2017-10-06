package ru.sofitlabs.firstwebapp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

public interface TestEntityService {

    void add(TestEntity testEntity);

    List<TestEntity> getAll();
}

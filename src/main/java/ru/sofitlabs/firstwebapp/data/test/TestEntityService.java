package ru.sofitlabs.firstwebapp.data.test;

import java.util.List;

public interface TestEntityService {

    void add(TestEntity testEntity);

    List<TestEntity> getAll();
}

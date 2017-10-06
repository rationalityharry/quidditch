package ru.sofitlabs.firstwebapp.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface TestRepository extends JpaRepository<TestEntity, Long> {

    List<TestEntity> findByNumber(final int number);

}

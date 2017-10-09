package ru.sofitlabs.firstwebapp.data.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional

public class UserEntityServiceImpl implements UserEntityService {
    @Autowired
    private UserRepository repository;

    @Override
    public void add(UserEntity userEntity) {
        repository.save(userEntity);
    }

    @Override
    public List<UserEntity> getAll() {
        return repository.findAll();
    }

    public List<UserEntity> getAllByLogin(String login) {
        return repository.findAllByLogin(login);
    }
}

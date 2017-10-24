package ru.sofitlabs.firstwebapp.data.user;

import org.apache.catalina.User;
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
    public UserEntity add(UserEntity userEntity) {
        return repository.save(userEntity);
    }

    @Override
    public List<UserEntity> getAll() {
        return repository.findAll();
    }

    public List<UserEntity> getAllByLogin(String login) {
        return repository.findAllByLogin(login);
    }

    public List<UserEntity> getAllbyId(long id) {
        return repository.findAllById(id);
    }

    public String getPasswordbyLogin(String login) {
        return repository.findAllByLogin(login).get(0).getPassword();
    }

    public UserEntity getOneById(long id) {
        return repository.findOne(id);
    }

    public UserEntity getOneByLogin(String login) {
        final List<UserEntity> allByLogin = repository.findAllByLogin(login);
        if (allByLogin.isEmpty()) {
            return null;
        }
        return allByLogin.get(0);
    }
}

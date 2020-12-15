package ru.quidditch.webapp.data.serviceimplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.repository.UserRepository;
import ru.quidditch.webapp.data.service.UserService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserEntity save(UserEntity userEntity) {
        return repository.save(userEntity);
    }

    @Override
    public List<UserEntity> getAll() {
        return repository.findAllByDisabledIsFalse();
    }

    public List<UserEntity> getAllByLogin(String login) {
        return repository.findAllByLogin(login);
    }

    public UserEntity getOneByLogin(String login) {
        final List<UserEntity> allByLogin = repository.findAllByLogin(login);
        if (allByLogin.isEmpty()) {
            return null;
        }
        return allByLogin.get(0);
    }

    @Override
    public UserEntity getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<UserEntity> getAllDisabled() {
        return repository.findAllByEnabledIsFalse();
    }


}

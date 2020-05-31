package ru.quidditch.webapp.data.serviceimplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.quidditch.webapp.data.entity.Role;
import ru.quidditch.webapp.data.repository.RoleRepository;
import ru.quidditch.webapp.data.service.RoleService;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository repository;

    @Override
    public Role getByName(String name) {
        return repository.getByName(name);
    }

    @Override
    public List<Role> getAll() {
        return repository.findAll();
    }

}

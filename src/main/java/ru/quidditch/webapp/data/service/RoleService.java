package ru.quidditch.webapp.data.service;

import ru.quidditch.webapp.data.entity.Role;

import java.util.List;

public interface RoleService {

    Role getByName(String name);

    List<Role> getAll();
}

package ru.quidditch.webapp.controller;

import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.enums.Roles;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractController {

    boolean checkUser(HttpServletRequest request, Roles role) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        return user != null && role.equals(user.getRole());
    }
    boolean checkUser(UserEntity user, Roles role) {
        return user != null && role.equals(user.getRole());
    }

}

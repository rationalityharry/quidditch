package ru.quidditch.webapp.controller;

import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.enums.Roles;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public abstract class AbstractController {

    boolean checkUser(HttpServletRequest request, Roles role) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        return user != null && role.equals(user.getRole());
    }
    boolean checkUser(UserEntity user, Roles role) {
        return user != null && role.equals(user.getRole());
    }
    boolean checkUser(HttpServletRequest request, List<Roles> roles) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        return user != null && roles.contains(user.getRole());
    }
    boolean checkUser(UserEntity user, List<Roles> roles) {
        return user != null && roles.contains(user.getRole());
    }

}

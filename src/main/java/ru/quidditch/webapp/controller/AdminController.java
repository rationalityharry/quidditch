package ru.quidditch.webapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.quidditch.webapp.data.entity.Role;
import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.service.RoleService;
import ru.quidditch.webapp.data.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/users", method = GET)
    @ResponseBody
    public List<UserEntity> getUsers(HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if (user != null && user.getRoles().contains(roleService.getByName("admin"))) {
            return userService.getAll();
        }
        return null;
    }

    @RequestMapping(value = "/roles", method = GET)
    @ResponseBody
    public List<Role> getRoles(HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if (user != null && user.getRoles().contains(roleService.getByName("admin"))) {
            return roleService.getAll();
        }
        return null;
    }

}

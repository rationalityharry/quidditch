package ru.quidditch.webapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.quidditch.webapp.core.MailSender;
import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.enums.Roles;
import ru.quidditch.webapp.data.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailSender mailSender;

    @RequestMapping(value = "/users", method = GET)
    public ResponseEntity<List<UserEntity>> getUsers(HttpServletRequest request, Principal principal) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        System.out.println(principal.getName());
        if (user != null && user.getRole().equals(Roles.ADMINISTRATOR)) {
            return ResponseEntity.ok(userService.getAll());
        }
        return null;
    }

    @RequestMapping(value = "/disabledUsers", method = GET)
    public ResponseEntity<List<UserEntity>> getDisabledUsers(HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if (user != null && user.getRole().equals(Roles.ADMINISTRATOR)) {
            return ResponseEntity.ok(userService.getAllDisabled());
        }
        return null;
    }

    @RequestMapping(value = "/enableUser/{userId}", method = GET)
    public ResponseEntity<Boolean> enableUser(@PathVariable final Long userId) {
        UserEntity user = userService.getById(userId);
        user.setEnabled(true);
        userService.add(user);
        mailSender.send(user.getEmail(), user.getName() + ", Your registration in quidditch ", user.getName() + ",\nВаша учетная запись активирована в команде квиддитча,\n Всегда ваш, Админ");
        return ResponseEntity.ok(true);
    }

}

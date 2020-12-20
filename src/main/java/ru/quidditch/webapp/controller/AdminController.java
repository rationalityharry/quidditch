package ru.quidditch.webapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.quidditch.webapp.core.MailSender;
import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.enums.Roles;
import ru.quidditch.webapp.data.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController extends AbstractController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailSender mailSender;

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDTO>> getUsers(HttpServletRequest request) {

        if (checkUserNull(request, Roles.ADMINISTRATOR)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        List<UserDTO> result = new ArrayList<>();
        userService.getAll().forEach(userEntity -> result.add(new UserDTO(userEntity)));
        return ResponseEntity.ok(result);

    }

    @GetMapping(value = "/disabledUsers")
    public ResponseEntity<List<UserDTO>> getDisabledUsers(HttpServletRequest request) {
        if (checkUserNull(request, Roles.ADMINISTRATOR)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        List<UserDTO> result = new ArrayList<>();
        userService.getAllDisabled().forEach(userEntity -> result.add(new UserDTO(userEntity)));
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/enableUser/{userId}")
    public ResponseEntity<Boolean> enableUser(HttpServletRequest request, @PathVariable final Long userId) {
        if (checkUserNull(request, Roles.ADMINISTRATOR)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        UserEntity user = userService.getById(userId);
        user.setEnabled(true);
        userService.save(user);
        mailSender.send(user.getEmail(), user.getName() + ", Your registration in quidditch ", user.getName() + ",\nВаша учетная запись активирована в команде квиддитча,\n Всегда ваш, Админ");
        return ResponseEntity.ok(true);
    }

    @GetMapping(value = "/disableUser/{userId}")
    public ResponseEntity<Boolean> disableUser(HttpServletRequest request, @PathVariable final Long userId) {
        if (checkUserNull(request, Roles.ADMINISTRATOR)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        UserEntity user = userService.getById(userId);
        user.setDisabled(true);
        userService.save(user);
        mailSender.send(user.getEmail(), user.getName() + ", Your account in quidditch ", user.getName() + ",\nВаша учетная запись заблокирована в команде квиддитча,\n Всегда ваш, Админ");
        return ResponseEntity.ok(true);
    }

    private static class UserDTO {
        String name;
        String surname;
        String faculty;
        Long id;

        public UserDTO() {
        }

        UserDTO(UserEntity userEntity) {
            this.name = userEntity.getName();
            this.surname = userEntity.getSurname();
            this.faculty = userEntity.getFaculty().getName();
            this.id = userEntity.getId();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getFaculty() {
            return faculty;
        }

        public void setFaculty(String faculty) {
            this.faculty = faculty;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}

package ru.quidditch.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.enums.Faculty;
import ru.quidditch.webapp.data.enums.Roles;
import ru.quidditch.webapp.data.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/")
public class AuthController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestBody final RegistrationUserData user) {
        if (!userService.getAllByLogin(user.login).isEmpty()) {
            return ResponseEntity.ok("");
        } else {
            final UserEntity createdUser = new UserEntity();
            createdUser.setLogin(user.login);
            createdUser.setPassword(user.password);
            createdUser.setSurname(user.surname);
            createdUser.setName(user.name);
            createdUser.setPatronimic(user.patronymic);
            createdUser.setEmail(user.email);
            createdUser.setFaculty(user.faculty != null ? Faculty.valueOf(user.faculty) : null);
            createdUser.setRole(Roles.valueOf(user.getRole().toUpperCase()));

            final UserEntity added = userService.add(createdUser);
            return ResponseEntity.ok(added.getLogin());
        }
    }


    @RequestMapping(value = "authorisation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Map<String, String>> auth(@RequestBody final UserData userReceived,
                                                    final HttpServletRequest request) {
        final UserEntity user = userService.getOneByLogin(userReceived.login);
        Map<String, String> data = new HashMap<>();
        if (user == null || !user.getPassword().equals(userReceived.password)) {
            data.put("role", "0");
            return ResponseEntity.ok(data);
        } else if (!user.isEnabled()) {
            data.put("role", "1");
            return ResponseEntity.ok(data);
        } else {
            final HttpSession session = request.getSession();
            session.setAttribute("user", user);
            data.put("role", user.getRole().getName());
            data.put("id", "" + user.getId());
            return ResponseEntity.ok(data);
        }
    }


    @RequestMapping(value = "exit", method = RequestMethod.POST)
    public ResponseEntity<Integer> logOut(final HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return ResponseEntity.ok(0);
    }


    @RequestMapping(value = {"authorisation", ""}, method = GET)
    public ModelAndView viewAuthorisation() {
        return new ModelAndView("main");
    }

    private static class RegistrationUserData {
        private String login;
        private String password;
        private String surname;
        private String name;
        private String patronymic;
        private String role;
        private String email;
        private String faculty;

        public RegistrationUserData() {
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(final String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(final String password) {
            this.password = password;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPatronymic() {
            return patronymic;
        }

        public void setPatronymic(String patronymic) {
            this.patronymic = patronymic;
        }

        public String getFaculty() {
            return faculty;
        }

        public void setFaculty(String faculty) {
            this.faculty = faculty;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    private static class UserData {
        private String login;
        private String password;


        public UserData() {
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(final String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(final String password) {
            this.password = password;
        }

    }

}

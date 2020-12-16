package ru.quidditch.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.quidditch.webapp.data.entity.*;
import ru.quidditch.webapp.data.enums.Faculty;
import ru.quidditch.webapp.data.enums.Roles;
import ru.quidditch.webapp.data.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private OperatorService operatorService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private CoachService coachService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private TeamService teamService;

    @PostMapping(value = "registration")
    public ResponseEntity<Long> add(@RequestBody final RegistrationUserData user) {
        if (!userService.getAllByLogin(user.login).isEmpty()) {
            return ResponseEntity.ok(-1L);
        } else {
            UserEntity createdUser = new UserEntity();
            Roles role = (Roles.valueOf(user.getRole().toUpperCase()));
            Faculty faculty = Faculty.valueOf(user.faculty.toUpperCase());
            TeamEntity team = teamService.getTeamByFaculty(faculty);
            if (team == null){
                team = new TeamEntity();
                team.setFaculty(faculty);
                team = teamService.save(team);
            }
            createdUser.setLogin(user.login);
            createdUser.setPassword(user.password);
            createdUser.setSurname(user.surname);
            createdUser.setName(user.name);
            createdUser.setPatronimic(user.patronymic);
            createdUser.setEmail(user.email);
            createdUser.setFaculty(faculty);
            createdUser.setRole(role);
            createdUser.setTeam(team);
            createdUser.setBirthday(user.birthdate);
            switch (createdUser.getRole()) {
                case OPERATOR:
                    createdUser = operatorService.save(new OperatorEntity(createdUser));
                    break;
                case PLAYER:
                    createdUser = playerService.save(new PlayerEntity(createdUser));
                    break;
                case COACH:
                    createdUser = coachService.save(new CoachEntity(createdUser));
                    break;
                case DOCTOR:
                    createdUser = doctorService.save(new DoctorEntity(createdUser));
                    break;
                case ADMINISTRATOR:
                    createdUser = userService.save(createdUser);
            }
            return ResponseEntity.ok(createdUser.getId());
        }
    }


    @PostMapping(value = "authorisation")
    public ResponseEntity<Map<String, String>> auth(@RequestBody final AuthData userReceived,
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
            data.put("login", user.getLogin());
            return ResponseEntity.ok(data);
        }
    }

    @GetMapping(value = "role")
    public ResponseEntity<Map<String, String>> role(final HttpServletRequest request) {
        final UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        Map<String, String> data = new HashMap<>();

        data.put("role", user.getRole().getName());
        data.put("id", "" + user.getId());
        data.put("login", user.getLogin());
        return ResponseEntity.ok(data);

    }


    @PostMapping(value = "exit")
    public ResponseEntity<Integer> logOut(final HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return ResponseEntity.ok(0);
    }

    @PostMapping(value = "changePassword")
    public ResponseEntity<Map<String, Integer>> changePassword(@RequestBody PasswordDTO passwords) {
        UserEntity user = userService.getById(passwords.userId);
        Map<String, Integer> response = new HashMap<>();
        if (user == null) {
            response.put("reason", 0);
            return ResponseEntity.ok(response);
        }
        if (!passwords.newPassword.equals(passwords.newPasswordConfirmation)) {
            response.put("reason", 1);
        } else if (!user.getPassword().equals(passwords.oldPassword)) {
            response.put("reason", 2);
        } else {
            user.setPassword(passwords.newPassword);
            userService.save(user);
            response.put("reason", 3);
        }
        return ResponseEntity.ok(response);
    }


    @GetMapping(value = {"authorisation", ""})
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
        private String birthdate;

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

        public String getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(String birthdate) {
            this.birthdate = birthdate;
        }
    }

    private static class AuthData {

        private String login;
        private String password;


        public AuthData() {
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

    private static class PasswordDTO {

        Long userId;
        String oldPassword;
        String newPassword;
        String newPasswordConfirmation;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getOldPassword() {
            return oldPassword;
        }

        public void setOldPassword(String oldPassword) {
            this.oldPassword = oldPassword;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }

        public String getNewPasswordConfirmation() {
            return newPasswordConfirmation;
        }

        public void setNewPasswordConfirmation(String newPasswordConfirmation) {
            this.newPasswordConfirmation = newPasswordConfirmation;
        }
    }
}

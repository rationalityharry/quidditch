package ru.quidditch.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.enums.Faculty;
import ru.quidditch.webapp.data.enums.Roles;
import ru.quidditch.webapp.data.service.ImageEntityService;
import ru.quidditch.webapp.data.service.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/user")
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

    @Autowired
    private ImageEntityService imageService;

    @GetMapping(value = "data/{userId}")
    public ResponseEntity<UserData> getEditInfo(HttpServletRequest request, @PathVariable Long userId) {
        UserEntity userInfo = userService.getById(userId);
        UserEntity currentUser = (UserEntity) request.getSession().getAttribute("user");
        if (userInfo == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        else return ResponseEntity.ok(new UserData(userInfo, currentUser));
    }


    @PostMapping(value = "edit/{userId}")
    public ResponseEntity<String> editUser(@RequestBody final UserData user, @PathVariable Long userId, HttpServletRequest request) {
        UserEntity userEntity = userService.getById(userId);
        if (userEntity == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {

            userEntity.setSurname(user.surname);
            userEntity.setName(user.name);
            userEntity.setPatronimic(user.patronymic);
            userEntity.setEmail(user.email);
            userEntity.setPhone(user.phone);
            userEntity.setInfo(user.info);
            if (userEntity.getUserKey() != null && user.imageId == 0) {
                userEntity.setUserKey(userEntity.getUserKey());
            } else {
                userEntity.setUserKey(imageService.getById(user.imageId));
            }

            userEntity.setBirthday(user.birthdate);

            UserEntity currentUser = (UserEntity) request.getSession().getAttribute("user");
            switch (currentUser.getRole()) {
                case ADMINISTRATOR:
                    userEntity.setLogin(user.login);
                    userEntity.setFaculty(user.faculty != null ? Faculty.valueOf(user.faculty.toUpperCase()) : null);
                    userEntity.setRole(Roles.valueOf(user.getRole().toUpperCase()));
                    userEntity.setPassword(user.password);
                    break;
            }

            UserEntity added = userService.save(userEntity);

            return ResponseEntity.ok(added.getLogin());
        }
    }

    private static class UserData {
        private String login;
        private String password;
        private String surname;
        private String name;
        private String patronymic;
        private String role;
        private String email;
        private String faculty;
        private String phone;
        private String info;
        private Long imageId;
        private Long id;
        private String birthdate;
        private boolean isAdmin;

        public UserData() {
        }

        public UserData(UserEntity entity, UserEntity currentUser) {
            this.login = entity.getLogin();
            this.password = entity.getPassword();
            this.surname = entity.getSurname();
            this.name = entity.getName();
            this.patronymic = entity.getPatronimic();
            this.role = entity.getRole().getName();
            this.email = entity.getEmail();
            this.faculty = entity.getFaculty().getName();
            this.phone = entity.getPhone();
            this.info = entity.getInfo();
            this.imageId = entity.getUserKey() != null ? entity.getUserKey().getAvatarImageid() : -1;
            this.id = entity.getId();
            this.isAdmin = currentUser.getRole().equals(Roles.ADMINISTRATOR);
            this.birthdate = entity.getBirthday();
        }


        public String getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(String birthdate) {
            this.birthdate = birthdate;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public Long getImageId() {
            return imageId;
        }

        public void setImageId(Long imageId) {
            this.imageId = imageId;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public boolean isAdmin() {
            return isAdmin;
        }

        public void setAdmin(boolean admin) {
            isAdmin = admin;
        }
    }

}

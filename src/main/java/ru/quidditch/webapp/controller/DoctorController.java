package ru.quidditch.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.quidditch.webapp.data.entity.PlayerEntity;
import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.enums.Faculty;
import ru.quidditch.webapp.data.enums.Roles;
import ru.quidditch.webapp.data.service.DoctorService;
import ru.quidditch.webapp.data.service.PlayerService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PlayerService playerService;

    @GetMapping(value = "/get")
    public ResponseEntity<List<PatientDTO>> getPatients(HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if (user != null && user.getRole().equals(Roles.DOCTOR)) {
            List<PatientDTO> result = new ArrayList<>();
            playerService.getAll().forEach(playerEntity ->
                    result.add(new PatientDTO(playerEntity.getName(),
                            playerEntity.getSurname(),
                            playerEntity.getFaculty(),
                            playerEntity.getBirthday(),
                            playerEntity.getId()
                            )));

            return ResponseEntity.ok(result);
        }
        return null;
    }

    private static class PatientDTO {
        String name;
        String surname;
        Faculty faculty;
        String age;
        Long id;

        public PatientDTO() {
        }

        public PatientDTO(String name, String surname, Faculty faculty, String birthday, Long id) {
            this.name = name;
            this.surname = surname;
            this.faculty = faculty;
            this.age = birthday;
            this.id = id;
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

        public Faculty getFaculty() {
            return faculty;
        }

        public void setFaculty(Faculty faculty) {
            this.faculty = faculty;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}

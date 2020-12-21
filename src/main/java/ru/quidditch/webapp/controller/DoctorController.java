package ru.quidditch.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.quidditch.webapp.data.entity.DoctorEntity;
import ru.quidditch.webapp.data.entity.MedicalExaminationEntity;
import ru.quidditch.webapp.data.entity.PlayerEntity;
import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.enums.Faculty;
import ru.quidditch.webapp.data.enums.Roles;
import ru.quidditch.webapp.data.service.DoctorService;
import ru.quidditch.webapp.data.service.MedicalExaminationService;
import ru.quidditch.webapp.data.service.PlayerService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/doctor")
public class DoctorController extends AbstractController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private MedicalExaminationService examinationService;

    @GetMapping(value = "/patients")
    public ResponseEntity<List<PatientDTO>> getPatients(HttpServletRequest request) {
        if (checkUserNull(request, Roles.DOCTOR)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
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

    @GetMapping(value = "/patients/{userId}")
    public ResponseEntity<Boolean> startExamination(HttpServletRequest request, @PathVariable final Long userId) {
        if (checkUserNull(request, Roles.DOCTOR)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        PlayerEntity player = playerService.findPlayerById(userId);
        return ResponseEntity.ok(true);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ExaminationDTO> createExamination(HttpServletRequest request, @RequestBody ExaminationDTO examinationDTO) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if (checkUserNull(request, Roles.DOCTOR)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        MedicalExaminationEntity examination = new MedicalExaminationEntity();
        examination.setDate(new Date());
        examination.setDoctor((DoctorEntity) user);
        examination.setInfo(examinationDTO.text);
        examination.setSick(examinationDTO.isSick);
        PlayerEntity player = playerService.findPlayerById(examinationDTO.id);
        player.setSick(examinationDTO.isSick);
        examination.setPlayer(player);
        examination = examinationService.save(examination);
        playerService.save(player);
        return ResponseEntity.ok(new ExaminationDTO(examination));
    }

    private static class PatientDTO {
        String name;
        String surname;
        Faculty faculty;
        String birthday;
        Long id;

        public PatientDTO() {
        }

        public PatientDTO(String name, String surname, Faculty faculty, String birthday, Long id) {
            this.name = name;
            this.surname = surname;
            this.faculty = faculty;
            this.birthday = birthday;
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

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    private static class ExaminationDTO {
        Long id;
        String text;
        Boolean isSick;

        public ExaminationDTO() {
        }

        ExaminationDTO(MedicalExaminationEntity exam) {
            this.id = exam.getId();
            this.text = exam.getInfo();
            this.isSick = exam.getSick();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Boolean getSick() {
            return isSick;
        }

        public void setSick(Boolean sick) {
            isSick = sick;
        }
    }
}

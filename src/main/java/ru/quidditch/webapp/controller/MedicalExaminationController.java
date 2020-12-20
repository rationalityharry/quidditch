package ru.quidditch.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.quidditch.webapp.data.entity.DoctorEntity;
import ru.quidditch.webapp.data.entity.MedicalExaminationEntity;
import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.enums.Roles;
import ru.quidditch.webapp.data.service.DoctorService;
import ru.quidditch.webapp.data.service.MedicalExaminationService;
import ru.quidditch.webapp.data.service.PlayerService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping(value = "/examinations")
public class MedicalExaminationController {

    @Autowired
    private MedicalExaminationService examinationService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private DoctorService doctorService;


//    @PostMapping(value = "/create")
//    public ResponseEntity<ExaminationDTO> createExamination(HttpServletRequest request, @RequestBody ExaminationDTO examinationDTO) {
//        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
//        if (!Roles.DOCTOR.equals(user.getRole())) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
//        }
//        MedicalExaminationEntity examination = new MedicalExaminationEntity();
//        examination.setDate(new Date());
//        examination.setDoctor((DoctorEntity) user);
//        examination.setInfo(examinationDTO.text);
//        examination.setSick(examinationDTO.isSick);
//        examination.setPlayer();
//
//        examination = examinationService.save(examination);
//        return ResponseEntity.ok(new ExaminationDTO(examination));
//    }

    private static class ExaminationDTO {
        Long id;
        String name;
        String surname;
        String text;
        Boolean isSick;

        public ExaminationDTO() {
        }

        public ExaminationDTO(Long id, String name, String surname, String text, Boolean isSick) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.text = text;
            this.isSick = isSick;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
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

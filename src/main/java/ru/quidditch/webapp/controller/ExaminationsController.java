package ru.quidditch.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.quidditch.webapp.data.entity.MedicalExaminationEntity;
import ru.quidditch.webapp.data.entity.PlayerEntity;
import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.enums.Roles;
import ru.quidditch.webapp.data.service.MedicalExaminationService;
import ru.quidditch.webapp.data.service.PlayerService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/examinations")
public class ExaminationsController extends AbstractController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private MedicalExaminationService medicalExaminationService;

    @GetMapping(value = "/all/{userId}")
    public ResponseEntity<Map<String, Object>> getExams(HttpServletRequest request, @PathVariable Long userId) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if (!checkUser(user, Arrays.asList(Roles.COACH, Roles.PLAYER, Roles.DOCTOR))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        PlayerEntity playerEntity = playerService.findPlayerById(userId);
        if (playerEntity == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);


        List<MedicalExaminationEntity> medicalExaminationsList = medicalExaminationService.getExaminationsForPlayer(playerEntity);

        List<ExamDTO> examList = new LinkedList<>();

        medicalExaminationsList.forEach(exam -> {
            ExamDTO examDTO = new ExamDTO(exam.getDate(), exam.getSick(), exam.getInfo());
            examList.add(examDTO);
        });

        Map<String, Object> result = new HashMap<>();
        result.put("name", playerEntity.getName());
        result.put("surname", playerEntity.getSurname());
        result.put("list", examList);

        return ResponseEntity.ok(result);
    }

    private static class ExamsDTO {
        String name;
        String surname;
        List<ExamDTO> list;

        public ExamsDTO() {
        }

        ExamsDTO(List<ExamDTO> list, PlayerEntity playerEntity) {
            this.name = playerEntity.getName();
            this.surname = playerEntity.getSurname();
            this.list = list;

        }
    }

    private static class ExamDTO {
        String date;
        Boolean isSick;
        String info;

        public ExamDTO() {
        }

        ExamDTO(Date date, Boolean isSick, String info) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            this.date = sdf.format(date);
            this.isSick = isSick;
            this.info = info;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Boolean getSick() {
            return isSick;
        }

        public void setSick(Boolean sick) {
            isSick = sick;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}

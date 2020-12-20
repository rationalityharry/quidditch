package ru.quidditch.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.quidditch.webapp.data.entity.PlayerEntity;
import ru.quidditch.webapp.data.entity.TrainingEntity;
import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.enums.Roles;
import ru.quidditch.webapp.data.service.PlayerService;
import ru.quidditch.webapp.data.service.TrainingService;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = "/training")
public class TrainingsController extends AbstractController {

    @Autowired
    private TrainingService trainingService;
    @Autowired
    private PlayerService playerService;

    @GetMapping(value = "/getTraining")
    public ResponseEntity<TrainingEntity> getTrainings(HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if (checkUserNull(user, Roles.COACH)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok(trainingService.getTrainingsByUserFaculty(user));
    }

    @PostMapping(value = "/saveTraining")
    public ResponseEntity<Boolean> changeTraining(HttpServletRequest request, @RequestBody TrainingDTO training) {

        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if (checkUserNull(user, Roles.COACH)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        TrainingEntity trainingEntity = trainingService.getTrainingsByUserFaculty(user);
        if (trainingEntity == null) {
            trainingEntity = new TrainingEntity();
        }
        trainingEntity.setFaculty(user.getFaculty());
        trainingEntity.setMonday(training.monday);
        trainingEntity.setTuesday(training.tuesday);
        trainingEntity.setWednesday(training.wednesday);
        trainingEntity.setThursday(training.thursday);
        trainingEntity.setFriday(training.friday);
        trainingEntity.setSaturday(training.saturday);
        trainingEntity.setSunday(training.sunday);

        trainingEntity.setMondayPlan(training.mondayPlan);
        trainingEntity.setTuesdayPlan(training.tuesdayPlan);
        trainingEntity.setWednesdayPlan(training.wednesdayPlan);
        trainingEntity.setThursdayPlan(training.thursdayPlan);
        trainingEntity.setFridayPlan(training.fridayPlan);
        trainingEntity.setSaturdayPlan(training.saturdayPlan);
        trainingEntity.setSundayPlan(training.sundayPlan);
        trainingService.save(trainingEntity);
        return ResponseEntity.ok(true);
    }

    @PostMapping(value = "/changeRating")
    public ResponseEntity<Boolean> changePlayerRating(HttpServletRequest request, @RequestBody PlayerDTO playerDTO) {

        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if (checkUserNull(user, Roles.COACH)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        PlayerEntity player = playerService.findPlayerById(playerDTO.id);
        if (player == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);

        player.setRate(player.getRate() + playerDTO.getRating());
        playerService.save(player);
        return ResponseEntity.ok(true);
    }

    @GetMapping(value = "/players")
    public ResponseEntity<List<PlayerDTO>> getPlayers(HttpServletRequest request) {

        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if (!checkUser(user, List.of(Roles.COACH, Roles.DOCTOR))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        List<PlayerEntity> players = playerService.findPlayersByFaculty(user.getFaculty());
        List<PlayerDTO> result = new LinkedList<>();
        if (players != null) {
            players.forEach(player -> {
                result.add(new PlayerDTO(player));
            });
        }
        return ResponseEntity.ok(result);
    }


    private static class TrainingDTO {

        private String monday;
        private String tuesday;
        private String wednesday;
        private String thursday;
        private String friday;
        private String saturday;
        private String sunday;
        private String mondayPlan;
        private String tuesdayPlan;
        private String wednesdayPlan;
        private String thursdayPlan;
        private String fridayPlan;
        private String saturdayPlan;
        private String sundayPlan;

        public TrainingDTO() {
        }

        public String getMonday() {
            return monday;
        }

        public void setMonday(String monday) {
            this.monday = monday;
        }

        public String getTuesday() {
            return tuesday;
        }

        public void setTuesday(String tuesday) {
            this.tuesday = tuesday;
        }

        public String getWednesday() {
            return wednesday;
        }

        public void setWednesday(String wednesday) {
            this.wednesday = wednesday;
        }

        public String getThursday() {
            return thursday;
        }

        public void setThursday(String thursday) {
            this.thursday = thursday;
        }

        public String getFriday() {
            return friday;
        }

        public void setFriday(String friday) {
            this.friday = friday;
        }

        public String getSaturday() {
            return saturday;
        }

        public void setSaturday(String saturday) {
            this.saturday = saturday;
        }

        public String getSunday() {
            return sunday;
        }

        public void setSunday(String sunday) {
            this.sunday = sunday;
        }

        public String getMondayPlan() {
            return mondayPlan;
        }

        public void setMondayPlan(String mondayPlan) {
            this.mondayPlan = mondayPlan;
        }

        public String getTuesdayPlan() {
            return tuesdayPlan;
        }

        public void setTuesdayPlan(String tuesdayPlan) {
            this.tuesdayPlan = tuesdayPlan;
        }

        public String getWednesdayPlan() {
            return wednesdayPlan;
        }

        public void setWednesdayPlan(String wednesdayPlan) {
            this.wednesdayPlan = wednesdayPlan;
        }

        public String getThursdayPlan() {
            return thursdayPlan;
        }

        public void setThursdayPlan(String thursdayPlan) {
            this.thursdayPlan = thursdayPlan;
        }

        public String getFridayPlan() {
            return fridayPlan;
        }

        public void setFridayPlan(String fridayPlan) {
            this.fridayPlan = fridayPlan;
        }

        public String getSaturdayPlan() {
            return saturdayPlan;
        }

        public void setSaturdayPlan(String saturdayPlan) {
            this.saturdayPlan = saturdayPlan;
        }

        public String getSundayPlan() {
            return sundayPlan;
        }

        public void setSundayPlan(String sundayPlan) {
            this.sundayPlan = sundayPlan;
        }
    }

    private static class PlayerDTO {
        private String name;
        private Integer rating;
        private String surname;
        private String patronymic;
        private String birthday;
        private Long id;

        public PlayerDTO() {
        }

        PlayerDTO(PlayerEntity player) {
            this.name = player.getName();
            this.surname = player.getSurname();
            this.patronymic = player.getPatronimic();
            this.birthday = player.getBirthday();
            this.id = player.getId();
            this.rating = player.getRate();
        }

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
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

        public String getPatronymic() {
            return patronymic;
        }

        public void setPatronymic(String patronymic) {
            this.patronymic = patronymic;
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


}

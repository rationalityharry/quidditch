package ru.quidditch.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.quidditch.webapp.data.entity.TrainingEntity;
import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.enums.Roles;
import ru.quidditch.webapp.data.service.TrainingService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/training")
public class TrainingsController {

    @Autowired
    private TrainingService trainingService;

    @GetMapping(value = "/get")
    public ResponseEntity<TrainingEntity> getTrainings(HttpServletRequest request) {
        UserEntity currentUser = (UserEntity) request.getSession().getAttribute("user");
        if (!Roles.COACH.equals(currentUser.getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        return ResponseEntity.ok(trainingService.getTrainingsByCoach(currentUser));
    }

    @PostMapping(value = "/saveTraining")
    public ResponseEntity<Boolean> changeTrainig(HttpServletRequest request, @RequestBody TrainingDTO training) {

        UserEntity currentUser = (UserEntity) request.getSession().getAttribute("user");
        if (!Roles.COACH.equals(currentUser.getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(false);
        }

        TrainingEntity trainingEntity = new TrainingEntity();
        trainingEntity.setFaculty(currentUser.getFaculty());
        trainingEntity.setMonday(training.monday);
        trainingEntity.setTuesday(training.tuesday);
        trainingEntity.setWednesday(training.wednesday);
        trainingEntity.setThursday(training.thursday);
        trainingEntity.setFriday(training.friday);
        trainingEntity.setSaturday(training.saturday);
        trainingEntity.setSunday(training.sunday);
        trainingService.add(trainingEntity);
        return ResponseEntity.ok(true);
    }


    private static class TrainingDTO {

        private String monday;
        private String tuesday;
        private String wednesday;
        private String thursday;
        private String friday;
        private String saturday;
        private String sunday;

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
    }


}

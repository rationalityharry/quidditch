package ru.quidditch.webapp.data.entity;


import ru.quidditch.webapp.data.enums.Faculty;

import javax.persistence.*;

@Table(name = "training")
@Entity
public class TrainingEntity {

    @Id
    @GeneratedValue
    private long trainingId;

    @Column
    private String monday;

    @Column
    private String tuesday;

    @Column
    private String wednesday;

    @Column
    private String thursday;

    @Column
    private String friday;

    @Column
    private String saturday;

    @Column
    private String sunday;

    @Column
    private Faculty faculty;

    public long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(long trainingId) {
        this.trainingId = trainingId;
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

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
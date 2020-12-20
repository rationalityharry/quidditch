package ru.quidditch.webapp.data.entity;


import ru.quidditch.webapp.data.enums.Faculty;

import javax.persistence.*;

@Table(name = "training")
@Entity
public class TrainingEntity {

    @Id
    @GeneratedValue
    private long id;

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
    private String mondayPlan;

    @Column
    private String tuesdayPlan;

    @Column
    private String wednesdayPlan;

    @Column
    private String thursdayPlan;

    @Column
    private String fridayPlan;

    @Column
    private String saturdayPlan;

    @Column
    private String sundayPlan;

    @Column
    private Faculty faculty;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

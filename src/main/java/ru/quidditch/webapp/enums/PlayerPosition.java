package ru.quidditch.webapp.enums;

public enum PlayerPosition {

    HUNTER("hunter"),
    BEATER("beater"),
    DEFENDER("defender"),
    SEEKER("seeker");

    private String name;

    PlayerPosition(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

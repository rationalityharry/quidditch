package ru.quidditch.webapp.data.enums;

public enum Roles {
    ADMINISTRATOR("administrator"),
    COACH("coach"),
    DOCTOR("doctor"),
    PLAYER("player"),
    OPERATOR("operator");

    private final String name;

    Roles(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

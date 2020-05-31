package ru.quidditch.webapp.enums;

public enum Faculty {

    RAVENCLAW("ravenclaw"),
    GRYFFINDOR("gryffindor"),
    HUFFLEPUFF("hufflepuff"),
    SLYTHERIN("slytherin"),
    ALL("all faculties");

    private String name;

    Faculty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

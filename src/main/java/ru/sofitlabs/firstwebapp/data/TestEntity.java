package ru.sofitlabs.firstwebapp.data;

import javax.persistence.*;

@Table(name = "my_entity")
@Entity
public class TestEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String text;

    @Column
    private long number;

    @Column
    private int anotherNumber;

    public TestEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(final long number) {
        this.number = number;
    }

    public int getAnotherNumber() {
        return anotherNumber;
    }

    public void setAnotherNumber(final int anotherNumber) {
        this.anotherNumber = anotherNumber;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", number=" + number +
                ", anotherNumber=" + anotherNumber +
                '}';
    }

}

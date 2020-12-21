package ru.quidditch.webapp.data.entity;

import ru.quidditch.webapp.data.enums.PlayerPosition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Table(name = "players")
@Entity
public class PlayerEntity extends UserEntity {

    @Column
    private int rate;

    @Column
    private PlayerPosition position;

    @Column
    private boolean captain;

    @Column
    private boolean isSick = false;

    @Column
    private boolean inStock = true;


    @OneToMany (mappedBy = "player")
    private List<MedicalExaminationEntity> examinations;

    public PlayerEntity() {

    }

    public PlayerEntity(UserEntity createdUser) {
        super(createdUser);
        this.isSick = false;
        this.captain = false;
        this.rate = 0;
        this.isSick = false;
        this.inStock = true;
        this.setPosition(PlayerPosition.NO_POSITION);
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public void setPosition(PlayerPosition position) {
        this.position = position;
    }

    public boolean isCaptain() {
        return captain;
    }

    public void setCaptain(boolean captain) {
        this.captain = captain;
    }

    public boolean isSick() {
        return isSick;
    }

    public void setSick(boolean sick) {
        isSick = sick;
    }

    public List<MedicalExaminationEntity> getExaminations() {
        return examinations;
    }

    public void setExaminations(List<MedicalExaminationEntity> examinations) {
        this.examinations = examinations;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}

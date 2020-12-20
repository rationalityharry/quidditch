package ru.quidditch.webapp.data.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Table(name = "doctors")
@Entity
public class DoctorEntity extends UserEntity {

    @OneToMany(mappedBy = "doctor")
    private List<MedicalExaminationEntity> examinations;

    public DoctorEntity() {
    }

    public DoctorEntity(UserEntity user) {
        super(user);
    }

}

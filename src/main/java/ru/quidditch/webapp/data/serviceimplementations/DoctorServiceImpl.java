package ru.quidditch.webapp.data.serviceimplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.quidditch.webapp.data.entity.DoctorEntity;
import ru.quidditch.webapp.data.repository.DoctorRepository;
import ru.quidditch.webapp.data.service.DoctorService;

import javax.transaction.Transactional;

@Service
@Transactional

public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository repository;

    @Override
    public DoctorEntity save(DoctorEntity doctorEntity) {
        return repository.save(doctorEntity);
    }
}

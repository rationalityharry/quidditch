package ru.quidditch.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.quidditch.webapp.data.service.MedicalExaminationService;
import ru.quidditch.webapp.data.service.PlayerService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/examinations")
public class MedicalExaminationController {

    @Autowired
    private MedicalExaminationService examinationService;

    @Autowired
    private PlayerService playerService;

    @PostMapping(value = "/create")
    public ResponseEntity<ExaminationDTO> createExamination(HttpServletRequest request, @RequestBody ExaminationDTO examinationDTO) {
    return null;
    }

    private static class ExaminationDTO {
        Long id;
        String headline;
        String content;
        String faculty;

        public ExaminationDTO() {
        }
    }
}

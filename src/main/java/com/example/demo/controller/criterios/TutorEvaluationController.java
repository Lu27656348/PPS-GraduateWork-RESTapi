package com.example.demo.controller.criterios;

import com.example.demo.entity.criterios.*;
import com.example.demo.service.criterios.TutorEvaluationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/criterios/tutor/evaluacion")
public class TutorEvaluationController {

    private final TutorEvaluationService tutorEvaluationService;

    public TutorEvaluationController(TutorEvaluationService tutorEvaluationService) {
        this.tutorEvaluationService = tutorEvaluationService;
    }

    /* Obtener Secciones por Modelo y por Escuela */
    @GetMapping("/escrita/escuela/{schoolName}/modelo/{criteriaModel}/seccion")
    public List<TutorReportEvaluationSeccion> getTutorReportSeccionByModelAndSchool(@PathVariable String schoolName, @PathVariable String criteriaModel){
        return tutorEvaluationService.getTutorReportSeccionByModelAndSchool(criteriaModel,schoolName);
    }

    @GetMapping("/oral/escuela/{schoolName}/modelo/{criteriaModel}/seccion")
    public List<TutorOralEvaluationSeccion> getTutorOralSeccionByModelAndSchool(@PathVariable String schoolName, @PathVariable String criteriaModel){
        return tutorEvaluationService.getTutorOralSeccionByModelAndSchool(criteriaModel,schoolName);
    }

    /* Obtener Criterios por modelo y por escuela */
    @GetMapping("/escrita/escuela/{schoolName}/modelo/{criteriaModel}")
    public List<TutorReportEvaluationCriteria> getTutorReportCriteriaByModelAndSchool(@PathVariable String schoolName, @PathVariable String criteriaModel){
        return tutorEvaluationService.getTutorReportCriteriaByModelAndSchool(criteriaModel,schoolName);
    }

    @GetMapping("/oral/escuela/{schoolName}/modelo/{criteriaModel}")
    public List<TutorOralEvaluationCriteria> getTutorOralCriteriaByModelAndSchool(@PathVariable String schoolName, @PathVariable String criteriaModel){
        return tutorEvaluationService.getTutorOralCriteriaByModelAndSchool(criteriaModel,schoolName);
    }

    /* Funciones para Evaluacion Oral de Jurado */
    @GetMapping("/oral/by/seccion/{id}")
    public List<TutorOralEvaluationCriteria> getTutorOralCriteriaBySeccion(@PathVariable Integer id){
        return tutorEvaluationService.getTutorOralCriteriaBySeccion(id);
    }

    @PostMapping("/oral/seccion")
    public TutorOralEvaluationSeccion createTutorOralEvaluationSeccion(@RequestBody TutorOralEvaluationSeccion tutorOralEvaluationSeccion){
        return tutorEvaluationService.createTutorOralEvaluationSeccion(tutorOralEvaluationSeccion);
    }

    @PutMapping("/oral/seccion")
    public TutorOralEvaluationSeccion changeTutorOralEvaluationSeccion(@RequestBody TutorOralEvaluationSeccion tutorOralEvaluationSeccion){
        return tutorEvaluationService.changeTutorOralEvaluationSeccion(tutorOralEvaluationSeccion);
    }

    @PostMapping("/oral")
    public TutorOralEvaluationCriteria createTutorOralEvaluationCriteria(@RequestBody TutorOralEvaluationCriteria tutorOralEvaluationCriteria){
        return tutorEvaluationService.createTutorOralEvaluationCriteria(tutorOralEvaluationCriteria);
    }

    @PutMapping("/oral")
    public TutorOralEvaluationCriteria changeTutorOralEvaluationCriteria(@RequestBody TutorOralEvaluationCriteria tutorOralEvaluationCriteria){
        return tutorEvaluationService.changeTutorOralEvaluationCriteria(tutorOralEvaluationCriteria);
    }

    /* Funciones para Evaluacion Escrita de Jurado */
    @GetMapping("/escrita/by/seccion/{id}")
    public List<TutorReportEvaluationCriteria> getTutorReportCriteriaBySeccion(@PathVariable Integer id){
        return tutorEvaluationService.getTutorReportCriteriaBySeccion(id);
    }

    @PostMapping("/escrita/seccion")
    public TutorReportEvaluationSeccion createTutorReportEvaluationSeccion(@RequestBody TutorReportEvaluationSeccion tutorReportEvaluationSeccion){
        return tutorEvaluationService.createTutorReportEvaluationSeccion(tutorReportEvaluationSeccion);
    }

    @PutMapping("/escrita/seccion")
    public TutorReportEvaluationSeccion changeTutorReportEvaluationSeccion(@RequestBody TutorReportEvaluationSeccion tutorReportEvaluationSeccion){
        return tutorEvaluationService.changeTutorReportEvaluationSeccion(tutorReportEvaluationSeccion);
    }

    @PostMapping("/escrita")
    public TutorReportEvaluationCriteria createTutorReportEvaluationCriteria(@RequestBody TutorReportEvaluationCriteria tutorReportEvaluationCriteria){
        return tutorEvaluationService.createTutorReportEvaluationCriteria(tutorReportEvaluationCriteria);
    }

    @PutMapping("/escrita")
    public TutorReportEvaluationCriteria changeTutorReportEvaluationCriteria(@RequestBody TutorReportEvaluationCriteria tutorReportEvaluationCriteria){
        return tutorEvaluationService.changeTutorReportEvaluationCriteria(tutorReportEvaluationCriteria);
    }

}

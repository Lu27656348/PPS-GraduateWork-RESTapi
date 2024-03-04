package com.example.demo.controller.criterios;

import com.example.demo.entity.criterios.JuryOralEvaluationCriteria;
import com.example.demo.entity.criterios.JuryOralEvaluationSeccion;
import com.example.demo.entity.criterios.JuryReportEvaluationCriteria;
import com.example.demo.entity.criterios.JuryReportEvaluationSeccion;
import com.example.demo.service.criterios.JuryEvaluationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/criterios/jurado/evaluacion")
public class JuryEvaluationController {
    private final JuryEvaluationService juryEvaluationService;

    public JuryEvaluationController(JuryEvaluationService juryEvaluationService) {
        this.juryEvaluationService = juryEvaluationService;
    }

    /* Obtener Secciones por Modelo y por Escuela */
    @GetMapping("/escrita/escuela/{schoolName}/modelo/{criteriaModel}/seccion")
    public List<JuryReportEvaluationSeccion> getJuryReportSeccionByModelAndSchool(@PathVariable String schoolName, @PathVariable String criteriaModel){
        return juryEvaluationService.getJuryReportSeccionByModelAndSchool(criteriaModel,schoolName);
    }

    @GetMapping("/oral/escuela/{schoolName}/modelo/{criteriaModel}/seccion")
    public List<JuryOralEvaluationSeccion> getJuryOralSeccionByModelAndSchool(@PathVariable String schoolName, @PathVariable String criteriaModel){
        return juryEvaluationService.getJuryOralSeccionByModelAndSchool(criteriaModel,schoolName);
    }

    /* Obtener Criterios por modelo y por escuela */
    @GetMapping("/escrita/escuela/{schoolName}/modelo/{criteriaModel}")
    public List<JuryReportEvaluationCriteria> getJuryReportCriteriaByModelAndSchool(@PathVariable String schoolName, @PathVariable String criteriaModel){
        return juryEvaluationService.getJuryReportCriteriaByModelAndSchool(criteriaModel,schoolName);
    }

    @GetMapping("/oral/escuela/{schoolName}/modelo/{criteriaModel}")
    public List<JuryOralEvaluationCriteria> getJuryOralCriteriaByModelAndSchool(@PathVariable String schoolName, @PathVariable String criteriaModel){
        return juryEvaluationService.getJuryOralCriteriaByModelAndSchool(criteriaModel,schoolName);
    }

    /* Funciones para Evaluacion Oral de Jurado */
    @GetMapping("/oral/by/seccion/{id}")
    public List<JuryOralEvaluationCriteria> getJuryOralCriteriaBySeccion(@PathVariable Integer id){
        return juryEvaluationService.getJuryOralCriteriaBySeccion(id);
    }

    @PostMapping("/oral/seccion")
    public JuryOralEvaluationSeccion createJuryOralEvaluationSeccion(@RequestBody JuryOralEvaluationSeccion juryOralEvaluationSeccion){
        return juryEvaluationService.createJuryOralEvaluationSeccion(juryOralEvaluationSeccion);
    }

    @PutMapping("/oral/seccion")
    public JuryOralEvaluationSeccion changeJuryOralEvaluationSeccion(@RequestBody JuryOralEvaluationSeccion juryOralEvaluationSeccion){
        return juryEvaluationService.changeJuryOralEvaluationSeccion(juryOralEvaluationSeccion);
    }

    @PostMapping("/oral")
    public JuryOralEvaluationCriteria createJuryOralEvaluationCriteria(@RequestBody JuryOralEvaluationCriteria juryOralEvaluationCriteria){
        return juryEvaluationService.createJuryOralEvaluationCriteria(juryOralEvaluationCriteria);
    }

    @PutMapping("/oral")
    public JuryOralEvaluationCriteria changeJuryOralEvaluationCriteria(@RequestBody JuryOralEvaluationCriteria juryOralEvaluationCriteria){
        return juryEvaluationService.changeJuryOralEvaluationCriteria(juryOralEvaluationCriteria);
    }

    /* Funciones para Evaluacion Escrita de Jurado */
    @GetMapping("/escrita/by/seccion/{id}")
    public List<JuryReportEvaluationCriteria> getJuryReportCriteriaBySeccion(@PathVariable Integer id){
        return juryEvaluationService.getJuryReportCriteriaBySeccion(id);
    }

    @PostMapping("/escrita/seccion")
    public JuryReportEvaluationSeccion createJuryReportEvaluationSeccion(@RequestBody JuryReportEvaluationSeccion juryReportEvaluationSeccion){
        return juryEvaluationService.createJuryReportEvaluationSeccion(juryReportEvaluationSeccion);
    }

    @PutMapping("/escrita/seccion")
    public JuryReportEvaluationSeccion changeJuryReportEvaluationSeccion(@RequestBody JuryReportEvaluationSeccion juryReportEvaluationSeccion){
        return juryEvaluationService.changeJuryReportEvaluationSeccion(juryReportEvaluationSeccion);
    }

    @PostMapping("/escrita")
    public JuryReportEvaluationCriteria createJuryReportEvaluationCriteria(@RequestBody JuryReportEvaluationCriteria juryReportEvaluationCriteria){
        return juryEvaluationService.createJuryReportEvaluationCriteria(juryReportEvaluationCriteria);
    }

    @PutMapping("/escrita")
    public JuryReportEvaluationCriteria changeJuryReportEvaluationCriteria(@RequestBody JuryReportEvaluationCriteria juryReportEvaluationCriteria){
        return juryEvaluationService.changeJuryReportEvaluationCriteria(juryReportEvaluationCriteria);
    }
}

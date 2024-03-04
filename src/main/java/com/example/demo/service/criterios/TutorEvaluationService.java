package com.example.demo.service.criterios;

import com.example.demo.entity.criterios.TutorOralEvaluationCriteria;
import com.example.demo.entity.criterios.TutorOralEvaluationSeccion;
import com.example.demo.entity.criterios.TutorReportEvaluationCriteria;
import com.example.demo.entity.criterios.TutorReportEvaluationSeccion;
import com.example.demo.repository.criterios.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorEvaluationService {

    private final TutorOralEvaluationSeccionRepository tutorOralEvaluationSeccionRepository;
    private final TutorOralEvaluationCriteriaRepository tutorOralEvaluationCriteriaRepository;

    private final TutorReportEvaluationSeccionRepository tutorReportEvaluationSeccionRepository;

    private final TutorReportEvaluationCriteriaRepository tutorReportEvaluationCriteriaRepository;


    public TutorEvaluationService(TutorOralEvaluationSeccionRepository tutorOralEvaluationSeccionRepository, TutorOralEvaluationCriteriaRepository tutorOralEvaluationCriteriaRepository, TutorReportEvaluationSeccionRepository tutorReportEvaluationSeccionRepository, TutorReportEvaluationCriteriaRepository tutorReportEvaluationCriteriaRepository) {
        this.tutorOralEvaluationSeccionRepository = tutorOralEvaluationSeccionRepository;
        this.tutorOralEvaluationCriteriaRepository = tutorOralEvaluationCriteriaRepository;
        this.tutorReportEvaluationSeccionRepository = tutorReportEvaluationSeccionRepository;
        this.tutorReportEvaluationCriteriaRepository = tutorReportEvaluationCriteriaRepository;
    }

    /* Servicios para Evaluacion Oral de Tutor*/
    public TutorOralEvaluationCriteria createTutorOralEvaluationCriteria (TutorOralEvaluationCriteria tutorOralEvaluationCriteria ){
        return tutorOralEvaluationCriteriaRepository.save(tutorOralEvaluationCriteria);
    }
    public TutorOralEvaluationSeccion createTutorOralEvaluationSeccion (TutorOralEvaluationSeccion tutorOralEvaluationSeccion ){
        return tutorOralEvaluationSeccionRepository.save(tutorOralEvaluationSeccion);
    }
    public TutorOralEvaluationSeccion changeTutorOralEvaluationSeccion (TutorOralEvaluationSeccion tutorOralEvaluationSeccion ){
        TutorOralEvaluationSeccion search = tutorOralEvaluationSeccionRepository.findById(tutorOralEvaluationSeccion.getSeccionId()).orElse(null);
        if( search != null){
            search.setSeccionName(tutorOralEvaluationSeccion.getSeccionName());
            search.setMaxNote(tutorOralEvaluationSeccion.getMaxNote());
            return tutorOralEvaluationSeccionRepository.save(search);
        }

        return null;
    }

    public TutorOralEvaluationCriteria changeTutorOralEvaluationCriteria (TutorOralEvaluationCriteria tutorOralEvaluationCriteria ){
        TutorOralEvaluationCriteria search = tutorOralEvaluationCriteriaRepository.findById(tutorOralEvaluationCriteria.getCriteriaId()).orElse(null);
        if( search != null){
            search.setCriteriaName(tutorOralEvaluationCriteria.getCriteriaName());
            search.setMaxNote(tutorOralEvaluationCriteria.getMaxNote());
            return tutorOralEvaluationCriteriaRepository.save(search);
        }

        return null;
    }

    /* Funciones para Evaluacion Escrita de Jurado */
    public TutorReportEvaluationCriteria createTutorReportEvaluationCriteria (TutorReportEvaluationCriteria tutorReportEvaluationCriteria ){
        return tutorReportEvaluationCriteriaRepository.save(tutorReportEvaluationCriteria);
    }

    public TutorReportEvaluationSeccion createTutorReportEvaluationSeccion (TutorReportEvaluationSeccion tutorReportEvaluationSeccion ){
        return tutorReportEvaluationSeccionRepository.save(tutorReportEvaluationSeccion);
    }

    public TutorReportEvaluationSeccion changeTutorReportEvaluationSeccion (TutorReportEvaluationSeccion tutorReportEvaluationSeccion ){
        TutorReportEvaluationSeccion search = tutorReportEvaluationSeccionRepository.findById(tutorReportEvaluationSeccion.getSeccionId()).orElse(null);
        if( search != null){
            search.setSeccionName(tutorReportEvaluationSeccion.getSeccionName());
            search.setMaxNote(tutorReportEvaluationSeccion.getMaxNote());
            return tutorReportEvaluationSeccionRepository.save(search);
        }

        return null;
    }

    public TutorReportEvaluationCriteria changeTutorReportEvaluationCriteria (TutorReportEvaluationCriteria tutorReportEvaluationCriteria ){
        TutorReportEvaluationCriteria search = tutorReportEvaluationCriteriaRepository.findById(tutorReportEvaluationCriteria.getCriteriaId()).orElse(null);
        if( search != null){
            search.setCriteriaName(tutorReportEvaluationCriteria.getCriteriaName());
            search.setMaxNote(tutorReportEvaluationCriteria.getMaxNote());
            return tutorReportEvaluationCriteriaRepository.save(search);
        }

        return null;
    }
    /* Obtener por modelo y por escuela */
    public List<TutorReportEvaluationSeccion> getTutorReportSeccionByModelAndSchool (String criteriaModel, String schoolName ){
        return tutorReportEvaluationSeccionRepository.getSeccionByModelAndSchool(criteriaModel,schoolName);
    }

    public List<TutorOralEvaluationSeccion> getTutorOralSeccionByModelAndSchool (String criteriaModel, String schoolName ){
        return tutorOralEvaluationSeccionRepository.getSeccionByModelAndSchool(criteriaModel,schoolName);
    }

    public List<TutorReportEvaluationCriteria> getTutorReportCriteriaByModelAndSchool (String criteriaModel, String schoolName ){
        return tutorReportEvaluationCriteriaRepository.getCriteriaByModelAndSchool(criteriaModel,schoolName);
    }

    public List<TutorOralEvaluationCriteria> getTutorOralCriteriaByModelAndSchool (String criteriaModel, String schoolName ){
        return tutorOralEvaluationCriteriaRepository.getCriteriaByModelAndSchool(criteriaModel,schoolName);
    }

    /* Obtener criterios por seccion */
    public List<TutorOralEvaluationCriteria> getTutorOralCriteriaBySeccion (Integer id){
        return tutorOralEvaluationCriteriaRepository.getCriteriaBySeccion(id);
    }

    public List<TutorReportEvaluationCriteria> getTutorReportCriteriaBySeccion (Integer id){
        return tutorReportEvaluationCriteriaRepository.getCriteriaBySeccion(id);
    }
}

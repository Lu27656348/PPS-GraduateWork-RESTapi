package com.example.demo.service.criterios;

import com.example.demo.entity.criterios.JuryOralEvaluationCriteria;
import com.example.demo.entity.criterios.JuryOralEvaluationSeccion;
import com.example.demo.entity.criterios.JuryReportEvaluationCriteria;
import com.example.demo.entity.criterios.JuryReportEvaluationSeccion;
import com.example.demo.repository.criterios.JuryOralEvaluationCriteriaRepository;
import com.example.demo.repository.criterios.JuryOralEvaluationSeccionRepository;
import com.example.demo.repository.criterios.JuryReportEvaluationCriteriaRepository;
import com.example.demo.repository.criterios.JuryReportEvaluationSeccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JuryEvaluationService {

    private final JuryOralEvaluationSeccionRepository juryOralEvaluationSeccionRepository;
    private final JuryOralEvaluationCriteriaRepository juryOralEvaluationCriteriaRepository;

    private final JuryReportEvaluationSeccionRepository juryReportEvaluationSeccionRepository;

    private final JuryReportEvaluationCriteriaRepository juryReportEvaluationCriteriaRepository;

    public JuryEvaluationService(JuryOralEvaluationSeccionRepository juryOralEvaluationSeccionRepository, JuryOralEvaluationCriteriaRepository juryOralEvaluationCriteriaRepository, JuryReportEvaluationSeccionRepository juryReportEvaluationSeccionRepository, JuryReportEvaluationCriteriaRepository juryReportEvaluationCriteriaRepository) {
        this.juryOralEvaluationSeccionRepository = juryOralEvaluationSeccionRepository;
        this.juryOralEvaluationCriteriaRepository = juryOralEvaluationCriteriaRepository;
        this.juryReportEvaluationSeccionRepository = juryReportEvaluationSeccionRepository;
        this.juryReportEvaluationCriteriaRepository = juryReportEvaluationCriteriaRepository;
    }

    /* Servicios para Evaluacion Oral de Jurado*/
    public JuryOralEvaluationCriteria createJuryOralEvaluationCriteria ( JuryOralEvaluationCriteria juryOralEvaluationCriteria ){
        return juryOralEvaluationCriteriaRepository.save(juryOralEvaluationCriteria);
    }
    public JuryOralEvaluationSeccion createJuryOralEvaluationSeccion (JuryOralEvaluationSeccion juryOralEvaluationSeccion ){
        return juryOralEvaluationSeccionRepository.save(juryOralEvaluationSeccion);
    }
    public JuryOralEvaluationSeccion changeJuryOralEvaluationSeccion (JuryOralEvaluationSeccion juryOralEvaluationSeccion ){
        JuryOralEvaluationSeccion search = juryOralEvaluationSeccionRepository.findById(juryOralEvaluationSeccion.getSeccionId()).orElse(null);
        if( search != null){
            search.setSeccionName(juryOralEvaluationSeccion.getSeccionName());
            search.setMaxNote(juryOralEvaluationSeccion.getMaxNote());
            return juryOralEvaluationSeccionRepository.save(search);
        }

        return null;
    }

    public JuryOralEvaluationCriteria changeJuryOralEvaluationCriteria (JuryOralEvaluationCriteria juryOralEvaluationCriteria ){
        JuryOralEvaluationCriteria search = juryOralEvaluationCriteriaRepository.findById(juryOralEvaluationCriteria.getCriteriaId()).orElse(null);
        if( search != null){
            search.setCriteriaName(juryOralEvaluationCriteria.getCriteriaName());
            search.setMaxNote(juryOralEvaluationCriteria.getMaxNote());
            return juryOralEvaluationCriteriaRepository.save(search);
        }

        return null;
    }

    /* Funciones para Evaluacion Escrita de Jurado */
    public JuryReportEvaluationCriteria createJuryReportEvaluationCriteria ( JuryReportEvaluationCriteria juryReportEvaluationCriteria ){
        return juryReportEvaluationCriteriaRepository.save(juryReportEvaluationCriteria);
    }

    public JuryReportEvaluationSeccion createJuryReportEvaluationSeccion ( JuryReportEvaluationSeccion juryReportEvaluationSeccion ){
        return juryReportEvaluationSeccionRepository.save(juryReportEvaluationSeccion);
    }

    public JuryReportEvaluationSeccion changeJuryReportEvaluationSeccion (JuryReportEvaluationSeccion juryReportEvaluationSeccion ){
        JuryReportEvaluationSeccion search = juryReportEvaluationSeccionRepository.findById(juryReportEvaluationSeccion.getSeccionId()).orElse(null);
        if( search != null){
            search.setSeccionName(juryReportEvaluationSeccion.getSeccionName());
            search.setMaxNote(juryReportEvaluationSeccion.getMaxNote());
            return juryReportEvaluationSeccionRepository.save(search);
        }

        return null;
    }

    public JuryReportEvaluationCriteria changeJuryReportEvaluationCriteria (JuryReportEvaluationCriteria juryReportEvaluationCriteria ){
        JuryReportEvaluationCriteria search = juryReportEvaluationCriteriaRepository.findById(juryReportEvaluationCriteria.getCriteriaId()).orElse(null);
        if( search != null){
            search.setCriteriaName(juryReportEvaluationCriteria.getCriteriaName());
            search.setMaxNote(juryReportEvaluationCriteria.getMaxNote());
            return juryReportEvaluationCriteriaRepository.save(search);
        }

        return null;
    }
    /* Obtener por modelo y por escuela */
    public List<JuryReportEvaluationSeccion> getJuryReportSeccionByModelAndSchool (String criteriaModel, String schoolName ){
        return juryReportEvaluationSeccionRepository.getSeccionByModelAndSchool(criteriaModel,schoolName);
    }

    public List<JuryOralEvaluationSeccion> getJuryOralSeccionByModelAndSchool (String criteriaModel, String schoolName ){
        return juryOralEvaluationSeccionRepository.getSeccionByModelAndSchool(criteriaModel,schoolName);
    }

    public List<JuryReportEvaluationCriteria> getJuryReportCriteriaByModelAndSchool (String criteriaModel, String schoolName ){
        return juryReportEvaluationCriteriaRepository.getCriteriaByModelAndSchool(criteriaModel,schoolName);
    }

    public List<JuryOralEvaluationCriteria> getJuryOralCriteriaByModelAndSchool (String criteriaModel, String schoolName ){
        return juryOralEvaluationCriteriaRepository.getCriteriaByModelAndSchool(criteriaModel,schoolName);
    }

    /* Obtener criterios por seccion */
    public List<JuryOralEvaluationCriteria> getJuryOralCriteriaBySeccion (Integer id){
        return juryOralEvaluationCriteriaRepository.getCriteriaBySeccion(id);
    }

    public List<JuryReportEvaluationCriteria> getJuryReportCriteriaBySeccion (Integer id){
        return juryReportEvaluationCriteriaRepository.getCriteriaBySeccion(id);
    }
}

package com.example.demo.repository.criterios;

import com.example.demo.entity.criterios.JuryReportEvaluationSeccion;
import com.example.demo.entity.criterios.TutorOralEvaluationSeccion;
import com.example.demo.entity.criterios.TutorReportEvaluationCriteria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JuryReportEvaluationSeccionRepository extends CrudRepository<JuryReportEvaluationSeccion,Integer> {
    @Query(
            value = "SELECT *\n" +
                    "FROM JuryReportEvaluationSeccion\n" +
                    "WHERE criteriaModel = :criteriaModel\n" +
                    "AND schoolName = :schoolName",
            nativeQuery = true
    )

    public List<JuryReportEvaluationSeccion> getSeccionByModelAndSchool(@Param("criteriaModel") String criteriaModel, @Param("schoolName") String schoolName);
}

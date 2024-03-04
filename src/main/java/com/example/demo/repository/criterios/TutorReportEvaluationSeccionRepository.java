package com.example.demo.repository.criterios;


import com.example.demo.entity.criterios.JuryOralEvaluationCriteria;
import com.example.demo.entity.criterios.TutorReportEvaluationSeccion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TutorReportEvaluationSeccionRepository extends CrudRepository<TutorReportEvaluationSeccion,Integer> {

    @Query(
            value = "SELECT *\n" +
                    "FROM TutorReportEvaluationSeccion\n" +
                    "WHERE criteriaModel = :criteriaModel\n" +
                    "AND schoolName = :schoolName",
            nativeQuery = true
    )

    public List<TutorReportEvaluationSeccion> getSeccionByModelAndSchool(@Param("criteriaModel") String criteriaModel, @Param("schoolName") String schoolName);
}

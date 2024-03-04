package com.example.demo.repository.criterios;

import com.example.demo.entity.criterios.JuryReportEvaluationCriteria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JuryReportEvaluationCriteriaRepository extends CrudRepository<JuryReportEvaluationCriteria, Integer> {

    @Query(
            value = "SELECT *\n" +
                    "FROM JuryReportEvaluationCriteria\n" +
                    "WHERE seccionId = :seccionId",
            nativeQuery = true
    )

    public List<JuryReportEvaluationCriteria> getCriteriaBySeccion(@Param("seccionId") Integer seccionId);

    @Query(
            value = "SELECT *\n" +
                    "FROM JuryReportEvaluationCriteria\n" +
                    "WHERE criteriaModel = :criteriaModel\n" +
                    "AND schoolName = :schoolName",
            nativeQuery = true
    )

    public List<JuryReportEvaluationCriteria> getCriteriaByModelAndSchool(@Param("criteriaModel") String criteriaModel, @Param("schoolName") String schoolName);

}

package com.example.demo.repository.criterios;

import com.example.demo.entity.criterios.JuryOralEvaluationCriteria;
import com.example.demo.entity.criterios.JuryOralEvaluationSeccion;
import com.example.demo.entity.criterios.JuryReportEvaluationCriteria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JuryOralEvaluationCriteriaRepository extends CrudRepository<JuryOralEvaluationCriteria,Integer> {

    @Query(
            value = "SELECT *\n" +
                    "FROM JuryOralEvaluationCriteria\n" +
                    "WHERE seccionId = :seccionId",
            nativeQuery = true
    )

    public List<JuryOralEvaluationCriteria> getCriteriaBySeccion(@Param("seccionId") Integer seccionId);

    @Query(
            value = "SELECT *\n" +
                    "FROM JuryOralEvaluationCriteria\n" +
                    "WHERE criteriaModel = :criteriaModel\n" +
                    "AND schoolName = :schoolName",
            nativeQuery = true
    )

    public List<JuryOralEvaluationCriteria> getCriteriaByModelAndSchool(@Param("criteriaModel") String criteriaModel, @Param("schoolName") String schoolName);
}

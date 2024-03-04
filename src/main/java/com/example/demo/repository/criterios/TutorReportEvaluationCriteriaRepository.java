package com.example.demo.repository.criterios;

import com.example.demo.entity.criterios.TutorOralEvaluationCriteria;
import com.example.demo.entity.criterios.TutorReportEvaluationCriteria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TutorReportEvaluationCriteriaRepository extends CrudRepository<TutorReportEvaluationCriteria,Integer> {

    @Query(
            value = "SELECT *\n" +
                    "FROM TutorReportEvaluationCriteria\n" +
                    "WHERE seccionId = :seccionId",
            nativeQuery = true
    )

    public List<TutorReportEvaluationCriteria> getCriteriaBySeccion(@Param("seccionId") Integer seccionId);

    @Query(
            value = "SELECT *\n" +
                    "FROM TutorReportEvaluationCriteria\n" +
                    "WHERE criteriaModel = :criteriaModel\n" +
                    "AND schoolName = :schoolName",
            nativeQuery = true
    )

    public List<TutorReportEvaluationCriteria> getCriteriaByModelAndSchool(@Param("criteriaModel") String criteriaModel, @Param("schoolName") String schoolName);
}

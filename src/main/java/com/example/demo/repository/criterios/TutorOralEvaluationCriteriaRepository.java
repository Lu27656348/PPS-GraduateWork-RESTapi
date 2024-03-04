package com.example.demo.repository.criterios;

import com.example.demo.entity.criterios.JuryOralEvaluationCriteria;
import com.example.demo.entity.criterios.TutorOralEvaluationCriteria;
import com.example.demo.entity.criterios.TutorReportEvaluationCriteria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TutorOralEvaluationCriteriaRepository extends CrudRepository<TutorOralEvaluationCriteria,Integer> {

    @Query(
            value = "SELECT *\n" +
                    "FROM TutorOralEvaluationCriteria\n" +
                    "WHERE seccionId = :seccionId",
            nativeQuery = true
    )

    public List<TutorOralEvaluationCriteria> getCriteriaBySeccion(@Param("seccionId") Integer seccionId);

    @Query(
            value = "SELECT *\n" +
                    "FROM TutorOralEvaluationCriteria\n" +
                    "WHERE criteriaModel = :criteriaModel\n" +
                    "AND schoolName = :schoolName",
            nativeQuery = true
    )

    public List<TutorOralEvaluationCriteria> getCriteriaByModelAndSchool(@Param("criteriaModel") String criteriaModel, @Param("schoolName") String schoolName);
}

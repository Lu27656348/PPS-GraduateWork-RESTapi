package com.example.demo.repository.criterios;

import com.example.demo.entity.criterios.JuryOralEvaluationSeccion;
import com.example.demo.entity.criterios.JuryReportEvaluationSeccion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JuryOralEvaluationSeccionRepository extends CrudRepository<JuryOralEvaluationSeccion,Integer> {
    @Query(
            value = "SELECT *\n" +
                    "FROM JuryOralEvaluationSeccion\n" +
                    "WHERE criteriaModel = :criteriaModel\n" +
                    "AND schoolName = :schoolName",
            nativeQuery = true
    )

    public List<JuryOralEvaluationSeccion> getSeccionByModelAndSchool(@Param("criteriaModel") String criteriaModel, @Param("schoolName") String schoolName);
}

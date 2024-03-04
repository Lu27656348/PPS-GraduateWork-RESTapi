package com.example.demo.repository;

import com.example.demo.entity.criterios.CriterioTutorEmpresarial;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriterioTutorEmpresarialRepository extends CrudRepository<CriterioTutorEmpresarial,Integer> {
    @Query(
            value = "SELECT *\n" +
                    "FROM criteriosTutorEmpresarial\n" +
                    "WHERE schoolName = :schoolName",
            nativeQuery = true
    )
    public List<CriterioTutorEmpresarial> obtenerCriteriosTutorEmpresarialBySchool(@Param("schoolName") String schoolName);
}

package com.example.demo.repository;

import com.example.demo.entity.Criteria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriteriaRepository extends CrudRepository<Criteria,Integer> {

    @Query(
            value = "SELECT *\n" +
                    "FROM reviewerCriteria\n" +
                    "WHERE schoolName = :schoolName\n" +
                    "AND criteriaModel = :criteriaModel",
            nativeQuery = true
    )
    public List<Criteria> getCriteriaByModelAndSchool(@Param("schoolName") String schoolname,@Param("criteriaModel") String criteriamodel);
}

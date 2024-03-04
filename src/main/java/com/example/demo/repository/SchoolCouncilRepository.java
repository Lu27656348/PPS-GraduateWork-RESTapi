package com.example.demo.repository;

import com.example.demo.entity.SchoolCouncils;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolCouncilRepository extends CrudRepository<SchoolCouncils,String> {

    @Query(
            value = "SELECT * \n" +
                    "FROM schoolCouncils \n" +
                    "WHERE schoolName = :schoolName",
            nativeQuery = true
    )
    public List<SchoolCouncils> getSchoolCouncilBySchool(String schoolName);
}

package com.example.demo.repository;

import com.example.demo.entity.Committee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitteeRepository extends CrudRepository<Committee,String> {

    @Query(
            value = "SELECT * \n" +
                    "FROM Committees \n" +
                    "WHERE schoolName = :schoolName",
            nativeQuery = true
    )
    public Iterable<Committee> getAllCommitteesBySchool(@Param("schoolName") String schoolName);
}

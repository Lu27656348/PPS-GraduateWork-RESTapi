package com.example.demo.repository;

import com.example.demo.entity.Professor;
import com.example.demo.entity.User;
import com.example.demo.interfaces.ProfessorResponseProjection;
import com.example.demo.responses.ProfessorResponse;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.example.demo.repository.ProfessorResponseConverter;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor,String> {

    @Query(
            value = "SELECT U.userDNI,U,userfirstname,U.userlastname FROM Users U, Professors P WHERE U.userDNI = P.professorDNI",
            nativeQuery = true
    )
    Iterable<ProfessorResponseProjection> getProfessorsData();


}

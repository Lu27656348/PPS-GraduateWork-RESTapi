package com.example.demo.responses;

import com.example.demo.entity.Professor;
import com.example.demo.entity.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfessorResponse {

    private String userDNI;
    private String userPassword;
    private String userFirstName;
    /*
    private String userLastName;
    private String userEmailUcab;
    private String userEmailAlt;
    private String userPhone;

    private String professorDNI;
    private String professorSchoolName;
    private String professorOffice;
    private String professorWorkExperience;
    private String professorGraduationYear;
    private String professorAddress;
    private String professorProfession;
    */
}

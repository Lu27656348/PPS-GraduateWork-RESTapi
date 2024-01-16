package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "professors")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Professor {
    @Id
    String professorDNI;

    @Column(name = "professorschoolname")
    String professorSchoolName;
    @Column(name = "professoroffice")
    String professorOffice;
    @Column(name = "professorworkexperience")
    Integer professorWorkExperience;
    @Column(name = "professorgraduationyear")
    Date professorGraduationYear;
    @Column(name = "professoraddress")
    String professorAddress;
    @Column(name = "professorprofession")
    String professorProfession;
    @Column(name = "professorrole")
    String professorRole = "Regular";

}

package com.example.demo.interfaces;

import jakarta.persistence.Column;

public interface StudentGraduateWorkProjection {

    @Column(name = "graduateworkid")
    String getGraduateworkid();

    @Column(name = "graduateworktitle")
    String getGraduateworktitle();

    @Column(name = "graduateworkstatuscode")
    String getGraduateworkstatuscode();

}

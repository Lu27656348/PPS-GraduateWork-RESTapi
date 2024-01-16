package com.example.demo.interfaces;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateGraduateWorkRequest {
    private String studentDNI;
    private String graduateWorkType;
    private String graduateWorkTitle;
    private String graduateWorkAcademicTutor;
    private Integer graduateWorkEnterprise;
    private String graduateWorkCoordinator;
    private String graduateWorkInCompanyTutor;
    private String studentDNI2;
}

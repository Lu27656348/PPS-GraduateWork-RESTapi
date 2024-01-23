package com.example.demo.interfaces.requests;

import lombok.Data;

@Data
public class VerifyAcademicTutorRevision {
    private String graduateWorkId;
    private String professorDNI;
    private Integer revisionNumber;
}

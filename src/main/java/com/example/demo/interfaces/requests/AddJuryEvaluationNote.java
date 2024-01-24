package com.example.demo.interfaces.requests;

import lombok.Data;

@Data
public class AddJuryEvaluationNote {
    private String juryDNI;
    private String studentDNI;
    private String graduateWorkId;
    private Integer criteriaId;
    private Integer evaluationNote;
}

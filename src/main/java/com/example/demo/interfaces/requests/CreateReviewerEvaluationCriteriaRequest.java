package com.example.demo.interfaces.requests;

import lombok.Data;

@Data
public class CreateReviewerEvaluationCriteriaRequest {
    private String graduateWorkId;
    private String professorDNI;
    private Integer reviewerCriteriaId;
}

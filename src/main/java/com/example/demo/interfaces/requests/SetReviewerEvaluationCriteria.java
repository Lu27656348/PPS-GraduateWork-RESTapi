package com.example.demo.interfaces.requests;

import lombok.Data;

@Data
public class SetReviewerEvaluationCriteria {
    private String professorDNI;
    private String graduateWorkId;
    private Integer reviewerCriteriaId;
}

package com.example.demo.interfaces.requests;

import lombok.Data;

@Data
public class GetReviewerEvaluationCriteria {
    private String graduateWorkId;
    private String professorDNI;
}

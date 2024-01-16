package com.example.demo.interfaces.requests;

import lombok.Data;

@Data
public class CreateReviewerEvaluationRequest {
    private String graduateWorkId;
    private String professorDNI;
}

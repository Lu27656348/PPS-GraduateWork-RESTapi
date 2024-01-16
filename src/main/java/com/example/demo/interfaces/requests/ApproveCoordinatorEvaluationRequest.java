package com.example.demo.interfaces.requests;

import lombok.Data;

@Data
public class ApproveCoordinatorEvaluationRequest {

    private String graduateWorkId;
    private String professorDNI;
    private String comments;

}

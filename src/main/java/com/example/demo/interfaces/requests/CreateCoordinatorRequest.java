package com.example.demo.interfaces.requests;

import lombok.Data;

@Data
public class CreateCoordinatorRequest {
    private String professorDNI;
    private String graduateWorkId;
    private Integer revisionNumber;
    private String revisionResult;
    private String coordinatorComments;

}

package com.example.demo.interfaces.requests;

import lombok.Data;

@Data
public class CreateApproveCoordinatorRequest {
    private String professorDNI;
    private String graduateWorkId;
}

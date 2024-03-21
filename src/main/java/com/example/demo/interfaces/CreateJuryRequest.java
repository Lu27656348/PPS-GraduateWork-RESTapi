package com.example.demo.interfaces;

import lombok.Data;

@Data
public class CreateJuryRequest {
    private String graduateWorkId;
    private String professorDNI;
    private String schoolCouncilId;
    private String juryType;
    private String reemplazo;
}

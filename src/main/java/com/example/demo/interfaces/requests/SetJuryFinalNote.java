package com.example.demo.interfaces.requests;

import lombok.Data;

@Data
public class SetJuryFinalNote {
    private String juryDNI;
    private String studentDNI;
    private String graduateWorkId;
    private Integer evaluationNote;
}

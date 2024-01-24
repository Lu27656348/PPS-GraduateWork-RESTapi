package com.example.demo.interfaces.requests;

import lombok.Data;

@Data
public class ChargeFinalNoteRequest {
    private String graduateWorkId;
    private Integer finalNote;
    private String mention;
}

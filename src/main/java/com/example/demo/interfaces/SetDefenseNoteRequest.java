package com.example.demo.interfaces;

import lombok.Data;

@Data
public class SetDefenseNoteRequest {
    private String graduateWorkId;
    private String professorDNI;
    private Integer note;
}

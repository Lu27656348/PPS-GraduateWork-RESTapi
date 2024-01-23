package com.example.demo.interfaces.requests;

import lombok.Data;

import java.util.Date;

@Data
public class GetJuryDataRequest {
    private String graduateWorkId;
    private String juryDNI;
    private String juryType;
    private String schoolCouncilId;
    private Date designationDate;
    private Integer juryFinalNote;
    private String juryMention;
    private Boolean isPresent;

}

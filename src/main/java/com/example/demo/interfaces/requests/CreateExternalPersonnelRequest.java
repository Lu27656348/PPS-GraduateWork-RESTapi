package com.example.demo.interfaces.requests;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CreateExternalPersonnelRequest {
    private String externalPersonnelDNI;
    private Integer externalPersonnelEnterpriseId;
    private String externalPersonnelProfession;
    private String externalPersonnelOffice;
    private String externalPersonnelAddress;
    private String externalPersonnelGraduationYear;
    private String externalPersonnelWorkExperience;
}

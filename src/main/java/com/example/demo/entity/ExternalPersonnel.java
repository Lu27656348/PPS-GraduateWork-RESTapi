package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "externalpersonnel")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExternalPersonnel {
    @Id
    @Column(name = "externalpersonneldni")
    private String externalPersonnelDNI;

    @Column(name = "externalpersonnelenterpriseid")
    private Integer externalPersonnelEnterpriseId;

    @Column( name = "externalpersonnelprofession")
    private String externalPersonnelProfession;

    @Column( name = "externalpersonneloffice")
    private String externalPersonnelOffice;

    @Column( name = "externalpersonneladdress")
    private String externalPersonnelAddress;

    @Column( name = "externalpersonnelgraduationyear")
    private String externalPersonnelGraduationYear;

    @Column( name = "externalpersonnelworkexperience")
    private String externalPersonnelWorkExperience;

}

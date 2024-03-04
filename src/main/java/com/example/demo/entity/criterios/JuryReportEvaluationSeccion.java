package com.example.demo.entity.criterios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "juryreportevaluationseccion")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JuryReportEvaluationSeccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use database-assigned IDs
    @Column(name = "seccionid")
    private Integer seccionId;

    @Column(name = "seccionname")
    private String seccionName;

    @Column(name = "maxnote")
    private Integer maxNote;

    @Column(name = "criteriamodel")
    private String criteriaModel;

    @Column(name = "schoolname")
    private String schoolName;
}

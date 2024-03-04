package com.example.demo.entity.criterios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tutorreportevaluationcriteria")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TutorReportEvaluationCriteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use database-assigned IDs
    @Column(name = "criteriaid")
    private Integer criteriaId;

    @Column(name = "criterianame")
    private String criteriaName;

    @Column(name = "criteriadescription")
    private String criteriaDescription;

    @Column(name = "maxnote")
    private Integer maxNote;

    @Column(name = "seccionid")
    private Integer seccionId;

    @Column(name = "criteriamodel")
    private String criteriaModel;

    @Column(name = "schoolname")
    private String schoolName;
}

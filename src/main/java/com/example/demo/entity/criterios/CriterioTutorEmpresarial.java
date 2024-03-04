package com.example.demo.entity.criterios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "criteriostutorempresarial")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriterioTutorEmpresarial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use database-assigned IDs
    @Column(name = "criteriaid")
    private Integer criteriaId;

    @Column(name = "criterianame")
    private String criteriaName;

    @Column(name = "maxnote")
    private Integer maxNote;

    @Column(name = "schoolname")
    private String schoolName;
}

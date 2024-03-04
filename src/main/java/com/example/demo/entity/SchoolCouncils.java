package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "schoolcouncils")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolCouncils {
    @Id
    @Column(name = "schoolcouncilid")
    private String schoolCouncilId;

    @Column(name = "schoolcouncildate")
    private Date schoolcouncildate;

    @Column(name = "schoolcounciltype")
    private String schoolSchoolType;

    @Column(name = "schoolname")
    private String schoolName;

}

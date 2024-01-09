package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "juries")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Jury {
    @Id
    private String graduateWorkId;
    private String professorDNI;
    private Date designationDate;
    private Integer juryType;
}

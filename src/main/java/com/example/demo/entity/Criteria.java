package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "reviewercriteria")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Criteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use database-assigned IDs
    @Column(name = "reviewercriteriaid")
    private Integer reviewerCriteriaId;

    @Column(name = "reviewercriteriadescription")
    private String reviewerCriteriaDescription;
}

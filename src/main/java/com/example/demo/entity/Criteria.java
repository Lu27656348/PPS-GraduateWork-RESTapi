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
@Table(name = "criteria")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Criteria {

    @Id
    private Integer criteriaid;

    @Column(name = "criteriadescription")
    private String criteriadescription;
}

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
@Table(name = "graduateworkcommittees")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Committee {

    @Id
    private String graduateworkcommittee;

    @Column(name = "graduateworkcommitteedate")
    private Date graduateworkcommitteedate;

}

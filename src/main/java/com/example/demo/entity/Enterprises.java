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
@Table(name = "enterprises")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Enterprises {
    @Id
    private Integer enterpriseid;

    @Column(name = "enterprisename")
    private String enterpriseName;

    @Column( name = "enterprisedescription")
    private String enterpriseDescription;
}

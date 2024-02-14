package com.example.demo.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enterpriseid;

    @Column(name = "enterprisename")
    private String enterpriseName;

    @Column( name = "enterprisedescription")
    private String enterpriseDescription;

    @Column( name = "enterpriseaddress")
    private String enterpriseAddress;
}

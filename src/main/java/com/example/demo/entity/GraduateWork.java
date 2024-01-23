package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "graduatework")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GraduateWork {
    @Id
    private String graduateworkid;

    @Column( name = "graduateworktype")
    private String graduateWorkType;

    @Column( name = "graduateworktitle")
    private String graduateWorkTitle;

    @Column( name = "graduateworkstatuscode")
    private Integer graduateWorkStatusCode;

    @Column( name = "graduateworkcoordinator")
    private String graduateWorkCoordinator;

    @Column( name = "graduateworkacademictutor")
    private String graduateWorkAcademicTutor;

    @Column( name = "graduateworkenterprise")
    private Integer graduateWorkEnterprise;

    @Column( name = "graduateworkincompanytutor")
    private String graduateWorkInCompanyTutor;

    @Column( name = "graduateworkreviewer")
    private String graduateWorReviewer;

    @Column( name = "graduateworkcommittee")
    private String graduateWorkCommittee;

    @Column( name = "committeeapprovaldate")
    private Date committeeApprovalDate;

    @Column( name = "graduateworkschoolcouncil")
    private String graduateWorkSchoolCouncil;

    @Column( name = "schoolcouncilapprovaldate")
    private Date schoolCouncilApprovalDate;

    @Column( name = "graduateworkdefensedate")
    private Date graduateWorkDefenseDate;

    @Column( name = "graduateworkdefenselocation")
    private String graduateWorkDefenseLocation;


}

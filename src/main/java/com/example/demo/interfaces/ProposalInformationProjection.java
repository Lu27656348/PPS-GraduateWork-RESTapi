package com.example.demo.interfaces;

import java.util.Date;

public interface ProposalInformationProjection {
    String getStudentDNI();
    String getGraduateWorkTitle();
    String getGraduateWorkId();

    Date getgraduateWorkDefenseDate();

    String getgraduateWorkDefenseLocation();
    String getStatusCode();

    String getStatusCodeDescription();
    Integer getGraduateWorkEnterprise();

    String getGraduateWorkType();

    String getGraduateWorkAcademicTutor();

    String getGraduateWorkinCompanyTutor();

    String getGraduateWorkCommittee();
    String getCommitteeApprovalDate();
}

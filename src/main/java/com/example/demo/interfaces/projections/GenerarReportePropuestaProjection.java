package com.example.demo.interfaces.projections;

import java.util.Date;

public interface GenerarReportePropuestaProjection {
    String getUserDNI();
    String getUserLastName();
    String getUserFirstName();
    String getUserEmail();
    String getUserPhone();
    String getGraduateWorkTitle();
    String getGraduateWorkType();
    String getEnterpriseName();
    String getGraduateWorkAcademicTutor();
    String getGraduateWorkInCompanyTutor();
    String getProfessorDNI();
    String getGraduateWorkId();
    Date getRevisionDate();
    String getGraduateWorkSchoolCouncil();
    Date getSchoolCouncilApprovalDate();
}

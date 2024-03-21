package com.example.demo.interfaces.projections;

import java.util.Date;

public interface GetGraduateWorkReviewerProjection {
    String getProfessorDNI();
    String getGraduateWorkId();

    Date getRevisionDate();
}

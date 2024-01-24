package com.example.demo.interfaces.projections;

public interface GetJuryEvaluationNoteProjection {
    String getJuryDNI();
    String getStudentDNI();

    String getGraduateWorkId();
    Integer getCriteriaId();
    String getCriteriaName();
    Integer getEvaluationNote();
    Integer getMaxNote();
}

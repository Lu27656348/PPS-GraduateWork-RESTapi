package com.example.demo.interfaces.projections;

import java.util.Date;

public interface GetJuryDataProjection
{
     String getGraduateWorkId();
     String getJuryDNI();
     String getJuryType();
     String getSchoolCouncilId();
     Date getDesignationDate();
     Integer getJuryFinalNote();
     String getJuryMention();
     Boolean getIsPresent();
}

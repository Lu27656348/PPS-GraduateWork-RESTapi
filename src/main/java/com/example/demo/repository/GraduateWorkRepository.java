package com.example.demo.repository;

import com.example.demo.entity.GraduateWork;
import com.example.demo.entity.User;
import com.example.demo.interfaces.*;
import com.example.demo.interfaces.projections.*;
import com.example.demo.interfaces.requests.GetGraduateWorkReviewer;
import com.example.demo.interfaces.requests.GetJuryDataRequest;
import com.example.demo.interfaces.responses.GetGraduateWorkReviewerResponse;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GraduateWorkRepository extends CrudRepository<GraduateWork,String> {
    @Query(
            value = "SELECT validarTrabajosDeGradoDeUsuario(:userDNI)",
            nativeQuery = true
    )
    Boolean validateStudentGraduateWorks(@Param("userDNI") String userDNI);

    @Query(
            value = "SELECT crearTrabajoDeGradoEstudiante(:studentDNI,:graduateWorkType,:graduateWorkTitle,:graduateWorkCoordinator,:graduateWorkAcademicTutor,:graduateWorkInCompanyTutor,:graduateWorkEnterprise,:studentDNI2)",
            nativeQuery = true
    )
    CreateGraduateWorkRequestProjection createGraduateWorkStudent(
            @Param("studentDNI") String studentDNI,
            @Param("graduateWorkType") String graduateWorkType,
            @Param("graduateWorkTitle") String graduateWorkTitle,
            @Param("graduateWorkCoordinator") String graduateWorkCoordinator,
            @Param("graduateWorkAcademicTutor") String graduateWorkAcademicTutor,
            @Param("graduateWorkInCompanyTutor") String graduateWorkInCompanyTutor,
            @Param("graduateWorkEnterprise") Integer graduateWorkEnterprise,
            @Param("studentDNI2") String studentDNI2
    );

    @Query(
            value = "SELECT U.userDNI, U.userfirstname, U.userlastname\n" +
                    "FROM Users U, Professors Pro\n" +
                    "WHERE U.userDNI = Pro.professorDNI\n" +
                    "UNION\n" +
                    "SELECT U.userDNI, U.userfirstname, U.userlastname\n" +
                    "FROM Users U, ExternalPersonnel Ext\n" +
                    "WHERE U.userDNI = Ext.externalpersonneldni",
            nativeQuery = true
    )
    Iterable<ProfessorResponseProjection> getInCompanyTutors();
    @Query(
            value = "SELECT *\n" +
                    "FROM GraduateWork\n" +
                    "WHERE graduateWorkStatusCode = :graduateWorkStatusCode",
            nativeQuery = true
    )
    public Iterable<GraduateWork> getGraduateWorkByStatus(@Param("graduateWorkStatusCode") Integer graduateWorkStatusCode);

    @Query(
            value = "SELECT Gw.graduateworkid,Gw.graduateworktitle,St.statuscode,St.statuscodedescription\n" +
                    "FROM GraduateWork Gw, StatusCodes St\n" +
                    "WHERE Gw.graduateworkstatuscode IN (10,11,12)\n" +
                    "AND St.statuscode = Gw.graduateworkstatuscode",
            nativeQuery = true
    )
    Iterable<ProposalInformationProjection> getProposals();

    @Query(
            value = "SELECT Us.*\n" +
                    "FROM Students Est, Student_GraduateWork Sgw, Users Us\n" +
                    "WHERE Sgw.graduateWorkId = :graduateWorkId\n" +
                    "AND Sgw.studentDNI =  Est.studentDNI\n" +
                    "AND Us.userDNI = Est.studentDNI",
            nativeQuery = true
    )
    Iterable<GetGraduateWorkStudentData> getGraduateWorkStudentData(@Param("graduateWorkId") String graduateWorkId);

    @Query(
            value = "SELECT Gw.*\n" +
                    "FROM GraduateWork Gw, student_graduatework sgw\n" +
                    "WHERE Gw.graduateworkid = sgw.graduateworkid\n" +
                    "AND Gw.graduateworkstatuscode NOT IN (400,401)\n" +
                    "AND sgw.studentDNI = :studentDNI",
            nativeQuery = true
    )
    GraduateWork getStudentProposal(@Param("studentDNI") String studentDNI);

    @Query(
            value = "SELECT Gw.graduateWorkId, Gw.graduateWorkTitle, St.statuscode,St.statuscodedescription\n" +
                    "FROM GraduateWork Gw,StatusCodes St\n" +
                    "WHERE Gw.graduateworkstatuscode NOT IN (400,401)\n" +
                    "AND Gw.graduateworkstatuscode = 20\n" +
                    "AND St.statuscode = Gw.graduateworkstatuscode",
            nativeQuery = true
    )
    Iterable<ProposalInformationProjection> getReviewersProposals();

    @Query(
            value = "SELECT Gw.graduateWorkId, Gw.graduateWorkTitle, St.statuscode,St.statuscodedescription\n" +
                    "FROM GraduateWork Gw,StatusCodes St\n" +
                    "WHERE Gw.graduateworkstatuscode NOT IN (400,401)\n" +
                    "AND Gw.graduateworkstatuscode = 30\n" +
                    "AND St.statuscode = Gw.graduateworkstatuscode",
            nativeQuery = true
    )
    Iterable<ProposalInformationProjection> getReviewersPending();

    @Query(
            value = "SELECT Gw.graduateWorkId, Gw.graduateWorkTitle\n" +
                    "FROM GraduateWork Gw\n" +
                    "WHERE Gw.graduateworkstatuscode NOT IN (400,401,100)\n" +
                    "AND Gw.graduateworkstatuscode = 70",
            nativeQuery = true
    )
    Iterable<ProposalInformationProjection> getDefensePending();

    @Query(
            value = "SELECT Gw.graduateWorkId, Gw.graduateWorkTitle\n" +
                    "FROM GraduateWork Gw\n" +
                    "WHERE Gw.graduateworkstatuscode NOT IN (400,401)\n" +
                    "AND Gw.graduateworkstatuscode = 40",
            nativeQuery = true
    )
    Iterable<ProposalInformationProjection> getCouncilPending();

    @Query(
            value = "SELECT Gw.graduateWorkId, Gw.graduateWorkTitle, St.statuscode,St.statuscodedescription\n" +
                    "FROM GraduateWork Gw, StatusCodes St\n" +
                    "WHERE Gw.graduateworkacademictutor = :professorDNI\n" +
                    "AND St.statuscode = Gw.graduateworkstatuscode\n" +
                    "AND Gw.graduateworkstatuscode IN(50,51)",
            nativeQuery = true
    )
    Iterable<ProposalInformationProjection> getAcademicTutorGraduateWorks(@Param("professorDNI") String professorDNI );

    @Query(
            value = "SELECT U.userLastName, U.userFirstName\n" +
                    "FROM Students Stu, GraduateWork Gw, student_graduatework Sgw, Users U\n" +
                    "WHERE Stu.studentDNI = Sgw.studentDNI\n" +
                    "AND Sgw.graduateworkid = Gw.graduateworkid\n" +
                    "AND U.userDNI = Stu.studentDNI\n" +
                    "AND Gw.graduateworkid = :graduateWorkId",
            nativeQuery = true
    )
    public GraduateWorkStudentProjection getGraduateWorkStudents(@Param("graduateWorkId") String graduateWorkId);

    @Query(
            value = "SELECT EXTRACT(DAY FROM ((Gw.schoolcouncilapprovaldate + INTERVAL '150 days') - CURRENT_DATE)) AS daysRemaining\n" +
            "FROM GraduateWork Gw\n" +
            "WHERE Gw.graduateWorkId = :graduateWorkId",
            nativeQuery = true
    )
    public Integer getRemainingDays (@Param("graduateWorkId") String graduateWorkId);

    @Query(
            value = "SELECT Gw.GraduateWorkId, Gw.GraduateWorkTitle\n" +
                    "FROM GraduateWork Gw\n" +
                    "WHERE Gw.graduateworkstatuscode NOT IN(400,401,100)\n" +
                    "AND Gw.graduateworkstatuscode = 60",
            nativeQuery = true
    )
    public Iterable<ProposalInformationProjection> getJuryPending();

    @Query(
            value = "SELECT Gw.GraduateWorkId, Gw.GraduateWorkTitle, Sgw.studentDNI\n" +
                    "FROM GraduateWork Gw, student_graduatework Sgw\n" +
                    "WHERE Gw.graduateworkstatuscode NOT IN(400,401,100)\n" +
                    "AND Sgw.GraduateWorkId = Gw.GraduateWorkId \n" +
                    "AND Gw.graduateworkstatuscode = 90",
            nativeQuery = true
    )
    public Iterable<ProposalInformationProjection> getCulminationPending();

    @Query(
            value = "SELECT Gw.graduateWorkId, Gw.graduateWorkTitle, Gw.graduateWorkDefenseDate, Gw.graduateWorkDefenseLocation\n" +
                    "FROM GraduateWork Gw, Juries Je\n" +
                    "WHERE Gw.graduateworkid = Je.graduateworkid\n" +
                    "AND Je.juryDNI = :professorDNI\n" +
                    "AND Gw.graduateworkstatuscode = 80\n" +
                    "GROUP BY Gw.graduateWorkId",
            nativeQuery = true
    )
    public Iterable<ProposalInformationProjection> getFinalEvaluationPending(@Param("professorDNI") String professorDNI);
    @Query(
            value = "SELECT asignarJurado(:professorDNI, :schoolCouncilId, :graduateWorkId, :juryType)",
            nativeQuery = true
    )
    public Boolean createJury(@Param("professorDNI") String professorDNI, @Param("schoolCouncilId") String schoolCouncilId,@Param("graduateWorkId") String graduateWorkId, @Param("juryType") String juryType);

    @Query(
            value = "SELECT setDefenseNote(:graduateWorkId,:professorDNI,:note)",
            nativeQuery = true
    )

    public Boolean setDefenseNote(@Param("graduateWorkId") String graduateWorkId, @Param("professorDNI") String professorDNI, @Param("note") Integer note);

    @Query(
            value = "SELECT approveCoordinatorEvaluation(:professorDNI,:graduateWorkId)",
            nativeQuery = true
    )

    public Boolean approveCoordinatorEvaluation(@Param("graduateWorkId") String graduateWorkId, @Param("professorDNI") String professorDNI);

    @Query(
            value = "SELECT createCoordinatorEvaluation(:professorDNI,:graduateWorkId,:revisionNumber, :revisionResult, :coordinatorComments)",
            nativeQuery = true
    )

    public Boolean createCoordinatorEvaluation(@Param("graduateWorkId") String graduateWorkId, @Param("professorDNI") String professorDNI, @Param("revisionNumber") Integer revisionNumber, @Param("revisionResult") String revisionResult, @Param("coordinatorComments") String coordinatorComments);

    @Query(
            value = "SELECT generateCoordinatorEvaluation(:professorDNI,:graduateWorkId, :revisionResult, :coordinatorComments)",
            nativeQuery = true
    )

    public Boolean generateCoordinatorEvaluation(@Param("graduateWorkId") String graduateWorkId, @Param("professorDNI") String professorDNI, @Param("revisionResult") String revisionResult, @Param("coordinatorComments") String coordinatorComments);

    @Query(
            value = "SELECT approvelastcoordinatorevaluation(:graduateWorkId)",
            nativeQuery = true
    )
    public Boolean approveLastCoordinatorEvaluation(@Param("graduateWorkId") String graduateWorkId);

    @Query(
            value = "SELECT verificarEstadoDePropuesta(:graduateWorkId)",
            nativeQuery = true
    )
    public Boolean getCoordinatorEvaluationStatus(@Param("graduateWorkId") String graduateWorkId);

    @Query(
            value = "SELECT aprobarRevisionCoordinador(:professorDNI,:graduateWorkId)",
            nativeQuery = true
    )
    public Integer aprobarRevisionCoordinador(@Param("professorDNI") String professorDNI,@Param("graduateWorkId") String graduateWorkId);

    @Query(
            value = "SELECT crearEvaluacionRevisor(:professorDNI,:graduateWorkId, :committeeId)",
            nativeQuery = true
    )
    public Boolean createReviewerEvaluation(@Param("professorDNI") String professorDNI,@Param("graduateWorkId") String graduateWorkId, @Param("committeeId") String committeeId);

    @Query(
            value = "SELECT Re.professorDNI\n" +
                    "FROM ReviewerEvaluation AS Re\n" +
                    "WHERE graduateWorkId = :graduateWorkId",
            nativeQuery = true
    )
    public GetGraduateWorkReviewerProjection getGraduateWorkReviewer(@Param("graduateWorkId") String graduateWorkId);
    @Query(
            value = "SELECT asignarCriterioAEvaluacionRevisor(:professorDNI,:graduateWorkId,:reviewerCriteriaId)",
            nativeQuery = true
    )
    public Boolean addCriteriaToReviewerEvaluation(@Param("professorDNI") String professorDNI,@Param("graduateWorkId") String graduateWorkId,@Param("reviewerCriteriaId") Integer reviewerCriteriaId);

    @Query(
            value = "SELECT Rec.reviewerCriteriaId, Rc.reviewerCriteriaDescription\n" +
                    "FROM ReviewerEvaluationCriteria AS Rec, ReviewerCriteria AS Rc\n" +
                    "WHERE Rec.graduateWorkId = :graduateWorkId\n" +
                    "AND Rec.professorDNI = :professorDNI\n" +
                    "AND Rec.reviewerCriteriaId = Rc.reviewerCriteriaId",
            nativeQuery = true
    )
    public Iterable<GetReviewerEvaluationCriteriaProjection> getReviewerEvaluationCriteria(@Param("professorDNI") String professorDNI, @Param("graduateWorkId") String graduateWorkId);


    /**************************Funciones para aprobar y
     *                         reprobar evaluaciones de Profesor Revisor********************************/
    @Query(
            value = "SELECT approveReviewerEvaluation(:graduateWorkId,:professorDNI,:comments)",
            nativeQuery = true
    )
    public Boolean approveReviewerEvaluation(@Param("professorDNI") String professorDNI,@Param("graduateWorkId") String graduateWorkId,@Param("comments") String comments);

    @Query(
            value = "SELECT reproveReviewerEvaluation(:graduateWorkId,:professorDNI,:comments)",
            nativeQuery = true
    )
    public Boolean reproveReviewerEvaluation(@Param("professorDNI") String professorDNI,@Param("graduateWorkId") String graduateWorkId,@Param("comments") String comments);

    @Query(
            value = "SELECT approveReviewerEvaluationCriteria(:graduateWorkId,:professorDNI,:reviewerCriteriaId)",
            nativeQuery = true
    )
    public Boolean approveReviewerEvaluationCriteria(@Param("professorDNI") String professorDNI,@Param("graduateWorkId") String graduateWorkId,@Param("reviewerCriteriaId") Integer reviewerCriteriaId);

    @Query(
            value = "SELECT reproveReviewerEvaluationCriteria(:graduateWorkId,:professorDNI,:reviewerCriteriaId)",
            nativeQuery = true
    )
    public Boolean reproveReviewerEvaluationCriteria(@Param("professorDNI") String professorDNI,@Param("graduateWorkId") String graduateWorkId,@Param("reviewerCriteriaId") Integer reviewerCriteriaId);

    @Query(
            value = "SELECT obtenerNumeroRevisionesCoordinador(:graduateWorkId)",
            nativeQuery = true
    )
    public Integer getReviewerCount(@Param("graduateWorkId") String graduateWorkId);

    @Query(
            value = "SELECT verificarRevisionesTutorAcademico(:graduateWorkId, :professorDNI)",
            nativeQuery = true
    )
    public Boolean verifyAcademicTutorRevisionPending(@Param("professorDNI") String professorDNI, @Param("graduateWorkId") String graduateWorkId);

    @Query(
            value = "SELECT addAcademicTutorEvaluation(:professorDNI, :graduateWorkId)",
            nativeQuery = true
    )
    public Boolean addAcademicTutorEvaluation(@Param("professorDNI") String professorDNI, @Param("graduateWorkId") String graduateWorkId);

    @Query(
            value = "SELECT obtenerNumeroRevisionTutorAcademico(:graduateWorkId, :professorDNI)",
            nativeQuery = true
    )
    public Integer getAcademicTutorGraduateWorksCount(@Param("graduateWorkId") String graduateWorkId, @Param("professorDNI") String professorDNI);

    @Query(
            value = "SELECT approveLastAcademicTutorEvaluation(:professorDNI, :graduateWorkId)",
            nativeQuery = true
    )
    public Boolean approveAcademicTutorRevision(@Param("professorDNI") String professorDNI, @Param("graduateWorkId") String graduateWorkId);
    @Query(
            value = "SELECT obtenerNumeroRevisionTutorAcademico(:graduateWorkId, :professorDNI)",
            nativeQuery = true
    )
    public Integer getAcademicTutorRevisionCount(@Param("graduateWorkId") String graduateWorkId, @Param("professorDNI") String professorDNI);

    @Query(
            value = "SELECT getIsJuryPresent(:juryDNI, :graduateWorkId)",
            nativeQuery = true
    )
    public Boolean getIsJuryPresent(@Param("juryDNI") String juryDNI, @Param("graduateWorkId") String graduateWorkId);

    @Query(
            value = "SELECT setIsJuryPresent(:juryDNI, :graduateWorkId,:isPresent)",
            nativeQuery = true
    )
    public Boolean setIsJuryPresent(@Param("juryDNI") String juryDNI, @Param("graduateWorkId") String graduateWorkId, @Param("isPresent") Boolean isPresent);
    @Query(
            value = "SELECT designateJuryPresident(:juryDNI, :graduateWorkId)",
            nativeQuery = true
    )
    public Boolean designateJuryPresident(@Param("juryDNI") String juryDNI, @Param("graduateWorkId") String graduateWorkId);

    @Query(
            value = "SELECT *\n" +
                    "FROM juryReportEvaluationCriteria\n" +
                    "WHERE criteriaModel = 'EXPERIMENTAL'",
            nativeQuery = true
    )
    public List<JuryReportExperimentalCriteriaProjection> getJuryReportExperimentalCriteria();

    @Query(
            value = "SELECT *\n" +
                    "FROM juryReportEvaluationSeccion\n" +
                    "WHERE criteriaModel = 'EXPERIMENTAL'",
            nativeQuery = true
    )
    public List<JuryReportExperimentalSeccionProjection> getJuryReportExperimentalSeccion();
    @Query(
            value = "SELECT *\n" +
                    "FROM juryOralEvaluationCriteria\n" +
                    "WHERE criteriaModel = 'EXPERIMENTAL'",
            nativeQuery = true
    )
    public List<JuryReportExperimentalCriteriaProjection> getJuryOralExperimentalCriteria();

    @Query(
            value = "SELECT *\n" +
                    "FROM juryOralEvaluationSeccion\n" +
                    "WHERE criteriaModel = 'EXPERIMENTAL'",
            nativeQuery = true
    )
    public List<JuryReportExperimentalSeccionProjection> getJuryOralExperimentalSeccion();

    @Query(
            value = "SELECT *\n" +
                    "FROM tutorOralEvaluationCriteria\n" +
                    "WHERE criteriaModel = 'EXPERIMENTAL'",
            nativeQuery = true
    )
    public List<JuryReportExperimentalCriteriaProjection> getTutorOralExperimentalCriteria();

    @Query(
            value = "SELECT *\n" +
                    "FROM tutorReportEvaluationCriteria\n" +
                    "WHERE criteriaModel = 'EXPERIMENTAL'",
            nativeQuery = true
    )
    public List<JuryReportExperimentalCriteriaProjection> getTutorReportExperimentalCriteria();
    @Query(
            value = "SELECT Ju.*\n" +
                    "FROM Juries Ju\n" +
                    "WHERE juryDNI = :juryDNI\n" +
                    "AND graduateWorkId = :graduateWorkId",
            nativeQuery = true
    )
    public GetJuryDataProjection getJuryData(@Param("juryDNI") String juryDNI, @Param("graduateWorkId") String graduateWorkId);

    @Query(
            value = "SELECT setJuryReportFinalNote(:juryDNI,:studentDNI, :graduateWorkId, :evaluationNote)",
            nativeQuery = true
    )
    public Boolean setJuryReportFinalNote(@Param("juryDNI") String juryDNI, @Param("studentDNI") String studentDNI,@Param("graduateWorkId") String graduateWorkId,@Param("evaluationNote") Integer evaluationNote);

    @Query(
            value = "SELECT setJuryOralFinalNote(:juryDNI,:studentDNI, :graduateWorkId, :evaluationNote)",
            nativeQuery = true
    )
    public Boolean setJuryOralFinalNote(@Param("juryDNI") String juryDNI, @Param("studentDNI") String studentDNI,@Param("graduateWorkId") String graduateWorkId,@Param("evaluationNote") Integer evaluationNote);

    @Query(
            value = "SELECT setTutorReportFinalNote(:juryDNI,:studentDNI, :graduateWorkId, :evaluationNote)",
            nativeQuery = true
    )
    public Boolean setTutorReportFinalNote(@Param("juryDNI") String juryDNI, @Param("studentDNI") String studentDNI,@Param("graduateWorkId") String graduateWorkId,@Param("evaluationNote") Integer evaluationNote);

    @Query(
            value = "SELECT setTutorOralFinalNote(:juryDNI,:studentDNI, :graduateWorkId, :evaluationNote)",
            nativeQuery = true
    )
    public Boolean setTutorOralFinalNote(@Param("juryDNI") String juryDNI, @Param("studentDNI") String studentDNI,@Param("graduateWorkId") String graduateWorkId,@Param("evaluationNote") Integer evaluationNote);
    @Query(
            value = "SELECT hasTutorSubmittedFinalNote(:juryDNI, :graduateWorkId)",
            nativeQuery = true
    )
    public Boolean getHasTutorSubmittedFinalNote(@Param("juryDNI") String juryDNI, @Param("graduateWorkId") String graduateWorkId);

    @Query(
            value = "SELECT hasJurySubmittedFinalNote(:juryDNI, :graduateWorkId)",
            nativeQuery = true
    )
    public Boolean getHasJurySubmittedFinalNote(@Param("juryDNI") String juryDNI, @Param("graduateWorkId") String graduateWorkId);

    @Query(
            value = "SELECT hasPresident(:graduateWorkId)",
            nativeQuery = true
    )
    public Boolean getHasJuryPresident(@Param("graduateWorkId") String graduateWorkId);
    @Query(
            value = "SELECT validateAllNotesSubmitted(:graduateWorkId)",
            nativeQuery = true
    )
    public Boolean getAllNotesValidation(@Param("graduateWorkId") String graduateWorkId);

    @Query(
            value = "SELECT obtainJuryOralEvaluationStudentNote(:graduateWorkId,:juryDNI)",
            nativeQuery = true
    )
    public Integer getJuryOralNote(@Param("graduateWorkId") String graduateWorkId,@Param("juryDNI") String juryDNI);

    @Query(
            value = "SELECT obtainJuryReportEvaluationStudentNote(:graduateWorkId,:juryDNI)",
            nativeQuery = true
    )
    public Integer getJuryReportNote(@Param("graduateWorkId") String graduateWorkId,@Param("juryDNI") String juryDNI);

    @Query(
            value = "SELECT obtainTutorOralEvaluationStudentNote(:graduateWorkId,:juryDNI)",
            nativeQuery = true
    )
    public Integer getTutorOralNote(@Param("graduateWorkId") String graduateWorkId,@Param("juryDNI") String juryDNI);

    @Query(
            value = "SELECT obtainTutorReportEvaluationStudentNote(:graduateWorkId,:juryDNI)",
            nativeQuery = true
    )
    public Integer getTutorReportNote(@Param("graduateWorkId") String graduateWorkId,@Param("juryDNI") String juryDNI);

    @Query(
            value = "SELECT obtainPresidentOralEvaluationStudentNote(:graduateWorkId,:juryDNI)",
            nativeQuery = true
    )
    public Integer getPresidentOralNote(@Param("graduateWorkId") String graduateWorkId,@Param("juryDNI") String juryDNI);

    @Query(
            value = "SELECT obtainPresidentReportEvaluationStudentNote(:graduateWorkId,:juryDNI)",
            nativeQuery = true
    )
    public Integer getPresidentReportNote(@Param("graduateWorkId") String graduateWorkId,@Param("juryDNI") String juryDNI);

    @Query(
            value = "SELECT *\n" +
                    "FROM Juries\n" +
                    "WHERE graduateWorkId = :graduateWorkId",
            nativeQuery = true
    )
    public List<GetJuryDataProjection> getGraduateWorkJuries(@Param("graduateWorkId") String graduateWorkId);

    @Query(
            value = "SELECT addJuryOralEvaluationNote(:juryDNI,:studentDNI,:graduateWorkId,:criteriaId,:evaluationNote)",
            nativeQuery = true
    )
    public Boolean addJuryOralEvaluationNote(@Param("juryDNI") String juryDNI,@Param("studentDNI") String studentDNI,@Param("graduateWorkId") String graduateWorkId,@Param("criteriaId") Integer criteriaId,@Param("evaluationNote") Integer evaluationNote);

    @Query(
            value = "SELECT addJuryReportEvaluationNote(:juryDNI,:studentDNI,:graduateWorkId,:criteriaId,:evaluationNote)",
            nativeQuery = true
    )
    public Boolean addJuryReportEvaluationNote(@Param("juryDNI") String juryDNI,@Param("studentDNI") String studentDNI,@Param("graduateWorkId") String graduateWorkId,@Param("criteriaId") Integer criteriaId,@Param("evaluationNote") Integer evaluationNote);


    @Query(
            value = "SELECT addTutorOralEvaluationNote(:juryDNI,:studentDNI,:graduateWorkId,:criteriaId,:evaluationNote)",
            nativeQuery = true
    )
    public Boolean addTutorOralEvaluationNote(@Param("juryDNI") String juryDNI,@Param("studentDNI") String studentDNI,@Param("graduateWorkId") String graduateWorkId,@Param("criteriaId") Integer criteriaId,@Param("evaluationNote") Integer evaluationNote);

    @Query(
            value = "SELECT addTutorReportEvaluationNote(:juryDNI,:studentDNI,:graduateWorkId,:criteriaId,:evaluationNote)",
            nativeQuery = true
    )
    public Boolean addTutorReportEvaluationNote(@Param("juryDNI") String juryDNI,@Param("studentDNI") String studentDNI,@Param("graduateWorkId") String graduateWorkId,@Param("criteriaId") Integer criteriaId,@Param("evaluationNote") Integer evaluationNote);

    @Query(
            value = "SELECT * FROM getTutorOralEvaluationSubmitted(:juryDNI,:studentDNI,:graduateWorkId)",
            nativeQuery = true
    )
    public List<GetJuryEvaluationNoteProjection> getTutorOralEvaluationNote(@Param("juryDNI") String juryDNI,@Param("studentDNI") String studentDNI,@Param("graduateWorkId") String graduateWorkId);
    @Query(
            value = "SELECT * FROM getTutorReportEvaluationSubmitted(:juryDNI,:studentDNI,:graduateWorkId)",
            nativeQuery = true
    )
    public List<GetJuryEvaluationNoteProjection> getTutorReportEvaluationNote(@Param("juryDNI") String juryDNI,@Param("studentDNI") String studentDNI,@Param("graduateWorkId") String graduateWorkId);
    @Query(
            value = "SELECT * FROM getJuryOralEvaluationSubmitted(:juryDNI,:studentDNI,:graduateWorkId)",
            nativeQuery = true
    )
    public List<GetJuryEvaluationNoteProjection> getJuryOralEvaluationNote(@Param("juryDNI") String juryDNI,@Param("studentDNI") String studentDNI,@Param("graduateWorkId") String graduateWorkId);
    @Query(
            value = "SELECT * FROM getJuryReportEvaluationSubmitted(:juryDNI,:studentDNI,:graduateWorkId)",
            nativeQuery = true
    )
    public List<GetJuryEvaluationNoteProjection> getJuryReportEvaluationNote(@Param("juryDNI") String juryDNI,@Param("studentDNI") String studentDNI,@Param("graduateWorkId") String graduateWorkId);

    @Query(
            value = "SELECT *\n" +
                    "FROM Juries\n" +
                    "WHERE graduateWorkId = :graduateWorkId\n" +
                    "AND juryType = :juryType",
            nativeQuery = true
    )
    public GetJuryDataProjection getGraduateWorkJuryByRol(@Param("graduateWorkId") String graduateWorkId, @Param("juryType") String juryType);

    @Query(
            value = "SELECT replaceCurrentJuryOralEvaluationNote(:juryDNI,:studentDNI,:graduateWorkId,:criteriaId,:evaluationNote)",
            nativeQuery = true
    )
    public Boolean changeJuryOralEvaluationNote(@Param("juryDNI") String juryDNI,@Param("studentDNI") String studentDNI,@Param("graduateWorkId") String graduateWorkId,@Param("criteriaId") Integer criteriaId,@Param("evaluationNote") Integer evaluationNote);

    @Query(
            value = "SELECT replaceCurrentJuryReportEvaluationNote(:juryDNI,:studentDNI,:graduateWorkId,:criteriaId,:evaluationNote)",
            nativeQuery = true
    )
    public Boolean changeJuryReportEvaluationNote(@Param("juryDNI") String juryDNI,@Param("studentDNI") String studentDNI,@Param("graduateWorkId") String graduateWorkId,@Param("criteriaId") Integer criteriaId,@Param("evaluationNote") Integer evaluationNote);


    @Query(
            value = "SELECT replaceCurrentTutorOralEvaluationNote(:juryDNI,:studentDNI,:graduateWorkId,:criteriaId,:evaluationNote)",
            nativeQuery = true
    )
    public Boolean changeTutorOralEvaluationNote(@Param("juryDNI") String juryDNI,@Param("studentDNI") String studentDNI,@Param("graduateWorkId") String graduateWorkId,@Param("criteriaId") Integer criteriaId,@Param("evaluationNote") Integer evaluationNote);

    @Query(
            value = "SELECT replaceCurrentTutorReportEvaluationNote(:juryDNI,:studentDNI,:graduateWorkId,:criteriaId,:evaluationNote)",
            nativeQuery = true
    )
    public Boolean changeTutorReportEvaluationNote(@Param("juryDNI") String juryDNI,@Param("studentDNI") String studentDNI,@Param("graduateWorkId") String graduateWorkId,@Param("criteriaId") Integer criteriaId,@Param("evaluationNote") Integer evaluationNote);

    @Query(
            value = "SELECT cargarNotaFinal(:graduateWorkId,:finalNote, :mention)",
            nativeQuery = true
    )
    public Boolean chargeFinalNote (@Param("graduateWorkId") String graduateWorkId,@Param("finalNote") Integer finalNote, @Param("mention") String mention);

    @Query(
            value = "SELECT validarTerminoProceso(:studentDNI)",
            nativeQuery = true
    )
    public Boolean isCulminated (@Param("studentDNI") String studentDNI);

}

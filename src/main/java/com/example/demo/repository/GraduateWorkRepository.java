package com.example.demo.repository;

import com.example.demo.entity.GraduateWork;
import com.example.demo.interfaces.*;
import com.example.demo.interfaces.projections.GetReviewerEvaluationCriteriaProjection;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
            value = "SELECT sgw.graduateworkid,Gw.graduateworktitle,St.statuscode,St.statuscodedescription,Sgw.studentDNI\n" +
                    "FROM GraduateWork Gw, student_graduatework Sgw, StatusCodes St\n" +
                    "WHERE Gw.graduateworkid = sgw.graduateworkid\n" +
                    "AND Gw.graduateworkstatuscode IN (10,11,12)\n" +
                    "AND St.statuscode = Gw.graduateworkstatuscode",
            nativeQuery = true
    )
    Iterable<ProposalInformationProjection> getProposals();

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
            value = "SELECT Gw.graduateWorkId, Gw.graduateWorkTitle, St.statuscode,St.statuscodedescription,sgw.studentDNI\n" +
                    "FROM GraduateWork Gw,student_graduatework Sgw,StatusCodes St\n" +
                    "WHERE Gw.graduateworkstatuscode NOT IN (400,401)\n" +
                    "AND Gw.graduateworkstatuscode = 20\n" +
                    "AND Gw.graduateworkid = sgw.graduateworkid\n" +
                    "AND St.statuscode = Gw.graduateworkstatuscode",
            nativeQuery = true
    )
    Iterable<ProposalInformationProjection> getReviewersProposals();

    @Query(
            value = "SELECT Gw.graduateWorkId, Gw.graduateWorkTitle, St.statuscode,St.statuscodedescription,sgw.studentDNI\n" +
                    "FROM GraduateWork Gw,student_graduatework Sgw,StatusCodes St\n" +
                    "WHERE Gw.graduateworkstatuscode NOT IN (400,401)\n" +
                    "AND Gw.graduateworkstatuscode = 30\n" +
                    "AND Gw.graduateworkid = sgw.graduateworkid\n"+
                    "AND St.statuscode = Gw.graduateworkstatuscode",
            nativeQuery = true
    )
    Iterable<ProposalInformationProjection> getReviewersPending();

    @Query(
            value = "SELECT Gw.graduateWorkId, Gw.graduateWorkTitle, sgw.studentDNI\n" +
                    "FROM GraduateWork Gw,student_graduatework Sgw\n" +
                    "WHERE Gw.graduateworkstatuscode NOT IN (400,401)\n" +
                    "AND Gw.graduateworkstatuscode = 70\n" +
                    "AND Gw.graduateworkid = sgw.graduateworkid",
            nativeQuery = true
    )
    Iterable<ProposalInformationProjection> getDefensePending();

    @Query(
            value = "SELECT Gw.graduateWorkId, Gw.graduateWorkTitle, sgw.studentDNI\n" +
                    "FROM GraduateWork Gw,student_graduatework Sgw\n" +
                    "WHERE Gw.graduateworkstatuscode NOT IN (400,401)\n" +
                    "AND Gw.graduateworkstatuscode = 40\n" +
                    "AND Gw.graduateworkid = sgw.graduateworkid",
            nativeQuery = true
    )
    Iterable<ProposalInformationProjection> getCouncilPending();

    @Query(
            value = "SELECT Gw.graduateWorkId, Gw.graduateWorkTitle, sgw.studentDNI\n" +
                    "FROM GraduateWork Gw, student_graduatework Sgw\n" +
                    "WHERE Gw.graduateworkacademictutor = :professorDNI\n" +
                    "AND Gw.graduateworkid = sgw.graduateworkid\n" +
                    "AND Gw.graduateworkstatuscode = 50;",
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
            value = "SELECT Gw.GraduateWorkId, Gw.GraduateWorkTitle, Sgw.studentDNI\n" +
                    "FROM GraduateWork Gw, student_graduatework Sgw\n" +
                    "WHERE Gw.finalSubmittionDate IS NOT NULL\n" +
                    "AND Gw.graduateworkstatuscode NOT IN(400,401,100)\n" +
                    "AND Sgw.GraduateWorkId = Gw.GraduateWorkId \n" +
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
            value = "SELECT Gw.graduateWorkId, Gw.graduateWorkTitle, Sgw.studentDNI\n" +
                    "FROM GraduateWork Gw, juries J, Student_graduatework Sgw\n" +
                    "WHERE Gw.graduateworkid = J.graduateworkid\n" +
                    "AND j.professordni = :professorDNI\n" +
                    "AND J.juryType = 1 \n" +
                    "AND Gw.graduateworkstatuscode = 80 \n" +
                    "AND Sgw.graduateworkid = Gw.graduateworkid",
            nativeQuery = true
    )
    public Iterable<ProposalInformationProjection> getFinalEvaluationPending(@Param("professorDNI") String professorDNI);
    @Query(
            value = "SELECT crearJurado(:graduateWorkId, :professorDNI, :juryType)",
            nativeQuery = true
    )
    public CreateJuryProjection createJury(@Param("graduateWorkId") String graduateWorkId, @Param("professorDNI") String professorDNI, @Param("juryType") Integer juryType);

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
            value = "SELECT crearEvaluacionRevisor(:professorDNI,:graduateWorkId)",
            nativeQuery = true
    )
    public Boolean createReviewerEvaluation(@Param("professorDNI") String professorDNI,@Param("graduateWorkId") String graduateWorkId);

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



}

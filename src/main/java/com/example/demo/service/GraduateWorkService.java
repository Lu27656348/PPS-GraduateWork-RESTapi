package com.example.demo.service;

import com.example.demo.entity.GraduateWork;
import com.example.demo.entity.User;
import com.example.demo.interfaces.*;
import com.example.demo.interfaces.projections.*;
import com.example.demo.interfaces.requests.*;
import com.example.demo.repository.GraduateWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GraduateWorkService{

    private final GraduateWorkRepository graduateWorkRepository;

    @Autowired
    public GraduateWorkService(GraduateWorkRepository graduateWorkRepository) {
        this.graduateWorkRepository = graduateWorkRepository;
    }

    public GraduateWork createGraduateWork (GraduateWork graduateWork){
        return this.graduateWorkRepository.save(graduateWork);
    }

    public Iterable<GraduateWork> getAllGraduateWorks (){
        return this.graduateWorkRepository.findAll();
    }

    public GraduateWork getGraduateWorkById (String id){
        return this.graduateWorkRepository.findById(id).orElse(null);
    }

    public GraduateWork getStudentProposal (String studentDNI){
        return graduateWorkRepository.getStudentProposal(studentDNI);
    }

    public Integer getReviewerCount (String graduateWorkId){
        return graduateWorkRepository.getReviewerCount(graduateWorkId);
    }

    public GraduateWork updateGraduateWork (String id, GraduateWork newGraduateWork){
        GraduateWork graduateWorkSearch = this.graduateWorkRepository.findById(id).orElse(null);
        if (graduateWorkSearch != null){
            graduateWorkSearch.setGraduateWorkCommittee(newGraduateWork.getGraduateWorkCommittee());
            graduateWorkSearch.setGraduateWorkType(newGraduateWork.getGraduateWorkType());
            graduateWorkSearch.setGraduateWorkTitle(newGraduateWork.getGraduateWorkTitle());
            graduateWorkSearch.setGraduateWorkStatusCode(newGraduateWork.getGraduateWorkStatusCode());
            graduateWorkSearch.setGraduateWorkCoordinator(newGraduateWork.getGraduateWorkCoordinator());
            return this.graduateWorkRepository.save(graduateWorkSearch);
        }
        return null;
    }

    public void deleteGraduateWork (String id){
        this.graduateWorkRepository.deleteById(id);
    }

    public Boolean validateStudentGraduateWorks (String userDNI){
        return graduateWorkRepository.validateStudentGraduateWorks(userDNI);
    }

    public CreateGraduateWorkRequestProjection createGraduateWorkStudent(CreateGraduateWorkRequest createGraduateWorkRequest){
        return graduateWorkRepository.createGraduateWorkStudent(createGraduateWorkRequest.getStudentDNI(),createGraduateWorkRequest.getGraduateWorkType(),createGraduateWorkRequest.getGraduateWorkTitle(),createGraduateWorkRequest.getGraduateWorkCoordinator(),createGraduateWorkRequest.getGraduateWorkAcademicTutor(),createGraduateWorkRequest.getGraduateWorkInCompanyTutor(),createGraduateWorkRequest.getGraduateWorkEnterprise(),createGraduateWorkRequest.getStudentDNI2());
    }

    public Iterable<ProfessorResponseProjection> getInCompanyTutors (){
        return graduateWorkRepository.getInCompanyTutors();
    }

    public Iterable<ProposalInformationProjection> getProposals(){
        return graduateWorkRepository.getProposals();
    }

    public Iterable<GetGraduateWorkStudentData> getGraduateWorkStudentData(String graduateWorkId){
        return graduateWorkRepository.getGraduateWorkStudentData(graduateWorkId);
    }

    public GraduateWork changeStatus(ChangeEstatusRequest changeEstatusRequest){
        GraduateWork graduateWorkSearch = graduateWorkRepository.findById(changeEstatusRequest.getGraduateWorkId()).orElse(null);
        if( graduateWorkSearch != null ){
            graduateWorkSearch.setGraduateWorkStatusCode(changeEstatusRequest.getEstatusCode());
            return graduateWorkRepository.save(graduateWorkSearch);
        }
        return null;
    }

    public GraduateWork setDefenseDate(SetDefenseDateRequest setDefenseDateRequest){
        GraduateWork graduateWorkSearch = graduateWorkRepository.findById(setDefenseDateRequest.getGraduateWorkId()).orElse(null);
        if( graduateWorkSearch != null ){
            System.out.println(setDefenseDateRequest.getGraduateWorkDefenseDate());
            graduateWorkSearch.setGraduateWorkDefenseDate(setDefenseDateRequest.getGraduateWorkDefenseDate());
            graduateWorkSearch.setGraduateWorkDefenseLocation(setDefenseDateRequest.getGraduateWorkDefenseLocation());
            System.out.println(graduateWorkSearch);
            return graduateWorkRepository.save(graduateWorkSearch);
        }
        return null;
    }

    public Iterable<ProposalInformationProjection> getFinalEvaluationPending(String professorDNI){
        return graduateWorkRepository.getFinalEvaluationPending(professorDNI);
    }

    public Iterable<ProposalInformationProjection> getReviewersProposals(){
        return graduateWorkRepository.getReviewersProposals();
    }

    public Iterable<ProposalInformationProjection> getReviewersPending(){
        return graduateWorkRepository.getReviewersPending();
    }

    public Iterable<ProposalInformationProjection> getCouncilPending(){
        return graduateWorkRepository.getCouncilPending();
    }

    public GraduateWork assignReviewer(String graduateWorkId, String graduateWorkCommittee, String graduateWorkReviewer){
        GraduateWork graduateWorkSearch = graduateWorkRepository.findById(graduateWorkId).orElse(null);
        if(graduateWorkSearch != null){
            graduateWorkSearch.setGraduateWorReviewer(graduateWorkReviewer);
            graduateWorkSearch.setGraduateWorkCommittee(graduateWorkCommittee);
            Date fechaActual = new Date();
            graduateWorkSearch.setCommitteeApprovalDate(fechaActual);
            return graduateWorkRepository.save(graduateWorkSearch);
        }
        return null;
    }

    public GraduateWork setSchoolCouncil(String graduateWorkId, String graduateWorkSchoolCouncil){
        GraduateWork graduateWorkSearch = graduateWorkRepository.findById(graduateWorkId).orElse(null);
        if(graduateWorkSearch != null){
            graduateWorkSearch.setGraduateWorkSchoolCouncil(graduateWorkSchoolCouncil);
            graduateWorkSearch.setSchoolCouncilApprovalDate(new Date());
            return graduateWorkRepository.save(graduateWorkSearch);
        }
        return null;
    }

    public Iterable<ProposalInformationProjection> getAcademicTutorGraduateWorks(String proffesorDNI){
        return graduateWorkRepository.getAcademicTutorGraduateWorks(proffesorDNI);
    }

    public GraduateWorkStudentProjection getGraduateWorkStudents(String graduateWorkId){
        return graduateWorkRepository.getGraduateWorkStudents(graduateWorkId);
    }

    public Integer getRemainingDays (String graduateWorkId){
        return graduateWorkRepository.getRemainingDays(graduateWorkId);
    }

    public Iterable<ProposalInformationProjection> getJuryPending(){
        return graduateWorkRepository.getJuryPending();
    }

    public Iterable<ProposalInformationProjection> getCulminationPending(){
        return graduateWorkRepository.getCulminationPending();
    }

    public Boolean createJury (String professorDNI, String schoolCouncilId, String graduateWorkId, String juryType){
        return graduateWorkRepository.createJury(professorDNI, schoolCouncilId,graduateWorkId, juryType);
    }

    public Iterable<ProposalInformationProjection> getDefensePending(){
        return graduateWorkRepository.getDefensePending();
    }

    public Boolean setDefenseNote(SetDefenseNoteRequest setDefenseNoteRequest){
        return graduateWorkRepository.setDefenseNote(setDefenseNoteRequest.getGraduateWorkId(),setDefenseNoteRequest.getProfessorDNI(),setDefenseNoteRequest.getNote());
    }

    public Boolean approveCoordinatorEvaluation(CreateApproveCoordinatorRequest createApproveCoordinatorRequest){
        return graduateWorkRepository.approveCoordinatorEvaluation(createApproveCoordinatorRequest.getGraduateWorkId(),createApproveCoordinatorRequest.getProfessorDNI());
    }

    public Boolean createCoordinatorEvaluation(CreateCoordinatorRequest createCoordinatorRequest){
        return graduateWorkRepository.createCoordinatorEvaluation(createCoordinatorRequest.getGraduateWorkId(),createCoordinatorRequest.getProfessorDNI(),createCoordinatorRequest.getRevisionNumber(),createCoordinatorRequest.getRevisionResult(),createCoordinatorRequest.getCoordinatorComments());
    }

    public Boolean generateCoordinatorEvaluation(CreateCoordinatorRequest createCoordinatorRequest){
        return graduateWorkRepository.generateCoordinatorEvaluation(createCoordinatorRequest.getGraduateWorkId(),createCoordinatorRequest.getProfessorDNI(),createCoordinatorRequest.getRevisionResult(),createCoordinatorRequest.getCoordinatorComments());
    }

    public Boolean approveLastCoordinatorEvaluation(CreateCoordinatorRequest createCoordinatorRequest){
        return graduateWorkRepository.approveLastCoordinatorEvaluation(createCoordinatorRequest.getGraduateWorkId());
    }

    public Boolean getCoordinatorEvaluationStatus(String graduateWorkId){
        return graduateWorkRepository.getCoordinatorEvaluationStatus(graduateWorkId);
    }

    public Integer aprobarRevisionCoordinador(String professorDNI, String graduateWorkId){
        return graduateWorkRepository.aprobarRevisionCoordinador(professorDNI,graduateWorkId);
    }

    public Boolean createReviewerEvaluation(String professorDNI, String graduateWorkId){
        return graduateWorkRepository.createReviewerEvaluation(professorDNI,graduateWorkId);
    }

    public Boolean addCriteriaToReviewerEvaluation(String professorDNI, String graduateWorkId, Integer reviewerCriteriaId){
        return graduateWorkRepository.addCriteriaToReviewerEvaluation(professorDNI,graduateWorkId,reviewerCriteriaId);
    }

    public Iterable<GetReviewerEvaluationCriteriaProjection> getReviewerEvaluationCriteria(String professorDNI, String graduateWorkId){
        return graduateWorkRepository.getReviewerEvaluationCriteria(professorDNI,graduateWorkId);
    }

    public GetGraduateWorkReviewerProjection getGraduateWorkReviewer (String graduateWorkId){
        return graduateWorkRepository.getGraduateWorkReviewer(graduateWorkId);
    }
    public Boolean approveReviewerEvaluation (String professorDNI, String graduateWorkId, String comments){
        return graduateWorkRepository.approveReviewerEvaluation(professorDNI,graduateWorkId,comments);
    }
    public Boolean approveReviewerEvaluationCriteria (String professorDNI, String graduateWorkId,Integer reviewerCriteriaId){
        return graduateWorkRepository.approveReviewerEvaluationCriteria(professorDNI,graduateWorkId,reviewerCriteriaId);
    }
    public Boolean reproveReviewerEvaluation (String professorDNI, String graduateWorkId, String comments){
        return graduateWorkRepository.reproveReviewerEvaluation(professorDNI,graduateWorkId,comments);
    }
    public Boolean reproveReviewerEvaluationCriteria (String professorDNI, String graduateWorkId, Integer reviewerCriteriaId){
        return graduateWorkRepository.reproveReviewerEvaluationCriteria(professorDNI,graduateWorkId,reviewerCriteriaId);
    }

    public Boolean verifyAcademicTutorRevisionPending(String professorDNI, String graduateWorkId){
        return graduateWorkRepository.verifyAcademicTutorRevisionPending(professorDNI,graduateWorkId);
    }

    public Boolean addAcademicTutorEvaluation (String professorDNI, String graduateWorkId) {
        return graduateWorkRepository.addAcademicTutorEvaluation(professorDNI,graduateWorkId);
    }

    public Integer getAcademicTutorGraduateWorksCount (String professorDNI, String graduateWorkId){
        return graduateWorkRepository.getAcademicTutorGraduateWorksCount(graduateWorkId,professorDNI);
    }

    public Boolean approveAcademicTutorRevision (String professorDNI, String graduateWorkId){
        return graduateWorkRepository.approveAcademicTutorRevision(professorDNI,graduateWorkId);
    }

    public Integer getAcademicTutorRevisionCount (String graduateWorkId, String professorDNI){
        return graduateWorkRepository.getAcademicTutorRevisionCount(graduateWorkId,professorDNI);
    }

    public Boolean getIsJuryPresent (String juryDNI, String graduateWorkId){
        return graduateWorkRepository.getIsJuryPresent(juryDNI,graduateWorkId);
    }

    public Boolean designateJuryPresident (String juryDNI, String graduateWorkId){
        return graduateWorkRepository.designateJuryPresident(juryDNI,graduateWorkId);
    }

    public Boolean setIsJuryPresent (String juryDNI, String graduateWorkId,Boolean isPresent){
        return graduateWorkRepository.setIsJuryPresent(juryDNI,graduateWorkId,isPresent);
    }

    public List<JuryReportExperimentalCriteriaProjection> getJuryReportExperimentalCriteria(){
        return graduateWorkRepository.getJuryReportExperimentalCriteria();
    }

    public List<JuryReportExperimentalCriteriaProjection> getJuryOralExperimentalCriteria(){
        return graduateWorkRepository.getJuryOralExperimentalCriteria();
    }

    public List<JuryReportExperimentalCriteriaProjection> getTutorReportExperimentalCriteria(){
        return graduateWorkRepository.getTutorReportExperimentalCriteria();
    }

    public List<JuryReportExperimentalCriteriaProjection> getTutorOralExperimentalCriteria(){
        return graduateWorkRepository.getTutorOralExperimentalCriteria();
    }

    public GetJuryDataProjection getJuryData(String juryDNI, String graduateWorkId){
        return graduateWorkRepository.getJuryData(juryDNI,graduateWorkId);
    }

    public Boolean setJuryReportFinalNote(String juryDNI, String studentDNI, String graduateWorkId, Integer evaluationNote){
        return graduateWorkRepository.setJuryReportFinalNote(juryDNI,studentDNI,graduateWorkId,evaluationNote);
    }

    public Boolean setJuryOralFinalNote(String juryDNI, String studentDNI, String graduateWorkId, Integer evaluationNote){
        return graduateWorkRepository.setJuryOralFinalNote(juryDNI,studentDNI,graduateWorkId,evaluationNote);
    }

    public Boolean setTutorReportFinalNote(String juryDNI, String studentDNI, String graduateWorkId, Integer evaluationNote){
        return graduateWorkRepository.setTutorReportFinalNote(juryDNI,studentDNI,graduateWorkId,evaluationNote);
    }

    public Boolean setTutorOralFinalNote(String juryDNI, String studentDNI, String graduateWorkId, Integer evaluationNote){
        return graduateWorkRepository.setTutorOralFinalNote(juryDNI,studentDNI,graduateWorkId,evaluationNote);
    }

    public Boolean getHasTutorSubmittedFinalNote (String juryDNI, String graduateWorkId){
        return graduateWorkRepository.getHasTutorSubmittedFinalNote(juryDNI,graduateWorkId);

    }

    public Boolean getHasJurySubmittedFinalNote (String juryDNI, String graduateWorkId){
        return graduateWorkRepository.getHasJurySubmittedFinalNote(juryDNI,graduateWorkId);

    }

    public Boolean getHasJuryPresident (String graduateWorkId){
        return graduateWorkRepository.getHasJuryPresident(graduateWorkId);

    }

    public Boolean getAllNotesValidation (String graduateWorkId){
        return graduateWorkRepository.getAllNotesValidation(graduateWorkId);

    }

    public Integer getJuryOralNote (String graduateWorkId,String juryDNI){
        return graduateWorkRepository.getJuryOralNote(graduateWorkId,juryDNI);
    }
    public Integer getJuryReportNote (String graduateWorkId,String juryDNI){
        return graduateWorkRepository.getJuryReportNote(graduateWorkId,juryDNI);
    }
    public Integer getTutorOralNote (String graduateWorkId,String juryDNI){
        return graduateWorkRepository.getTutorOralNote(graduateWorkId,juryDNI);
    }

    public Integer getTutorReportNote (String graduateWorkId,String juryDNI){
        return graduateWorkRepository.getTutorReportNote(graduateWorkId,juryDNI);
    }

    public Integer getPresidentOralNote (String graduateWorkId,String juryDNI){
        return graduateWorkRepository.getPresidentOralNote(graduateWorkId,juryDNI);
    }

    public Integer getPresidentReportNote (String graduateWorkId,String juryDNI){
        return graduateWorkRepository.getPresidentReportNote(graduateWorkId,juryDNI);
    }

    public List<GetJuryDataProjection> getGraduateWorkJuries (String graduateWorkId){
        return graduateWorkRepository.getGraduateWorkJuries(graduateWorkId);
    }

    public Boolean addJuryOralEvaluationNote(String juryDNI, String studentDNI, String graduateWorkId,Integer criteriaId, Integer evaluationNote){
        return graduateWorkRepository.addJuryOralEvaluationNote(juryDNI,studentDNI,graduateWorkId,criteriaId,evaluationNote);
    }
    public Boolean addJuryReportEvaluationNote(String juryDNI, String studentDNI, String graduateWorkId,Integer criteriaId, Integer evaluationNote){
        return graduateWorkRepository.addJuryReportEvaluationNote(juryDNI,studentDNI,graduateWorkId,criteriaId,evaluationNote);
    }
    public Boolean addTutorOralEvaluationNote(String juryDNI, String studentDNI, String graduateWorkId,Integer criteriaId, Integer evaluationNote){
        return graduateWorkRepository.addTutorOralEvaluationNote(juryDNI,studentDNI,graduateWorkId,criteriaId,evaluationNote);
    }
    public Boolean addTutorReportEvaluationNote(String juryDNI, String studentDNI, String graduateWorkId,Integer criteriaId, Integer evaluationNote){
        return graduateWorkRepository.addTutorReportEvaluationNote(juryDNI,studentDNI,graduateWorkId,criteriaId,evaluationNote);
    }

    public List<GetJuryEvaluationNoteProjection> getTutorOralEvaluationNote (String juryDNI, String studentDNI, String graduateWorkId){
        return graduateWorkRepository.getTutorOralEvaluationNote(juryDNI,studentDNI,graduateWorkId);
    }
    public List<GetJuryEvaluationNoteProjection> getTutorReportEvaluationNote (String juryDNI, String studentDNI, String graduateWorkId){
        return graduateWorkRepository.getTutorReportEvaluationNote(juryDNI,studentDNI,graduateWorkId);
    }
    public List<GetJuryEvaluationNoteProjection> getJuryOralEvaluationNote (String juryDNI, String studentDNI, String graduateWorkId){
        return graduateWorkRepository.getJuryOralEvaluationNote(juryDNI,studentDNI,graduateWorkId);
    }
    public List<GetJuryEvaluationNoteProjection> getJuryReportEvaluationNote (String juryDNI, String studentDNI, String graduateWorkId){
        return graduateWorkRepository.getJuryReportEvaluationNote(juryDNI,studentDNI,graduateWorkId);
    }

    public GetJuryDataProjection getGraduateWorkJuryByRol (String graduateWorkId, String juryType){
        return graduateWorkRepository.getGraduateWorkJuryByRol(graduateWorkId,juryType);
    }

    /******************/
    public Boolean changeJuryOralEvaluationNote(String juryDNI, String studentDNI, String graduateWorkId,Integer criteriaId, Integer evaluationNote){
        return graduateWorkRepository.changeJuryOralEvaluationNote(juryDNI,studentDNI,graduateWorkId,criteriaId,evaluationNote);
    }
    public Boolean changeJuryReportEvaluationNote(String juryDNI, String studentDNI, String graduateWorkId,Integer criteriaId, Integer evaluationNote){
        return graduateWorkRepository.changeJuryReportEvaluationNote(juryDNI,studentDNI,graduateWorkId,criteriaId,evaluationNote);
    }
    public Boolean changeTutorOralEvaluationNote(String juryDNI, String studentDNI, String graduateWorkId,Integer criteriaId, Integer evaluationNote){
        return graduateWorkRepository.changeTutorOralEvaluationNote(juryDNI,studentDNI,graduateWorkId,criteriaId,evaluationNote);
    }
    public Boolean changeTutorReportEvaluationNote(String juryDNI, String studentDNI, String graduateWorkId,Integer criteriaId, Integer evaluationNote){
        return graduateWorkRepository.changeTutorReportEvaluationNote(juryDNI,studentDNI,graduateWorkId,criteriaId,evaluationNote);
    }

    public Boolean chargeFinalNote(String graduateWorkId,Integer finalNote, String mention){
        return graduateWorkRepository.chargeFinalNote(graduateWorkId,finalNote,mention);
    }

    public Boolean isCulminated(String studentDNI){
        return graduateWorkRepository.isCulminated(studentDNI);
    }
}

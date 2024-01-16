package com.example.demo.service;

import com.example.demo.entity.GraduateWork;
import com.example.demo.interfaces.*;
import com.example.demo.interfaces.projections.GetReviewerEvaluationCriteriaProjection;
import com.example.demo.interfaces.requests.CreateApproveCoordinatorRequest;
import com.example.demo.interfaces.requests.CreateCoordinatorRequest;
import com.example.demo.interfaces.requests.GetReviewerEvaluationCriteria;
import com.example.demo.repository.GraduateWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
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
            graduateWorkSearch.setGraduateWorkDefenseDate(setDefenseDateRequest.getGraduateWorkDefenseDate());
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

    public CreateJuryProjection createJury (String graduateWorkId, String professorDNI, Integer juryType){
        return graduateWorkRepository.createJury(graduateWorkId, professorDNI,juryType);
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


}

package com.example.demo.controller;

import com.example.demo.entity.GraduateWork;
import com.example.demo.entity.User;
import com.example.demo.interfaces.*;

import com.example.demo.interfaces.projections.*;
import com.example.demo.interfaces.requests.*;
import com.example.demo.service.GraduateWorkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/graduate-work")
public class GraduateWorkController {
    private final GraduateWorkService graduateWorkService;

    public GraduateWorkController(GraduateWorkService graduateWorkService) {
        this.graduateWorkService = graduateWorkService;
    }

    @GetMapping
    public Iterable<GraduateWork> getAllGraduateWorks (){
        return graduateWorkService.getAllGraduateWorks();
    }

    @GetMapping("/by/status/{id}")
    public Iterable<GraduateWork> getGraduateWorkByStatus (@PathVariable  Integer id){
        return  graduateWorkService.getGraduateWorkByStatus(id);
    }
    @GetMapping("/validate/user/{id}")
    public Boolean validateStudentGraduateWorks(@PathVariable String id){
        return graduateWorkService.validateStudentGraduateWorks(id);
    }

    @GetMapping("/proposal/student/{id}")
    public GraduateWork getStudentProposal(@PathVariable String id){
        return graduateWorkService.getStudentProposal(id);
    }

    @PostMapping("/create/proposal")
    public CreateGraduateWorkRequestProjection createGraduateWorkStudent(@RequestBody CreateGraduateWorkRequest createGraduateWorkRequest){
        return graduateWorkService.createGraduateWorkStudent(createGraduateWorkRequest);
    }

    @GetMapping("/company/tutors")
    public Iterable<ProfessorResponseProjection> getInCompanyTutors(){
        return graduateWorkService.getInCompanyTutors();

    }

    @GetMapping("proposals")
    public Iterable<ProposalInformationProjection> getProposals(){


        return  graduateWorkService.getProposals();
    }

    @GetMapping("student/data/{id}")
    public ResponseEntity<Iterable<GetGraduateWorkStudentData>> getGraduateWorkStudentData(@PathVariable("id") String id){
        return  ResponseEntity.ok(graduateWorkService.getGraduateWorkStudentData(id));
    }
    @GetMapping("data/{id}")
    public GraduateWork getGraduateWorkById(@PathVariable("id") String id){
        return graduateWorkService.getGraduateWorkById(id);
    }

    @PutMapping("/change/estatus")
    public GraduateWork changeStatus(@RequestBody ChangeEstatusRequest changeEstatusRequest){
        return graduateWorkService.changeStatus(changeEstatusRequest);
    }

    @PutMapping("/change/defense/date")
    public GraduateWork setDefenseDate(@RequestBody SetDefenseDateRequest setDefenseDateRequest){
        return graduateWorkService.setDefenseDate(setDefenseDateRequest);
    }

    @GetMapping("/final/defense/pending/{id}")
    public Iterable<ProposalInformationProjection> getFinalDefensePending(@PathVariable String id){
        return graduateWorkService.getFinalEvaluationPending(id);
    }

    @GetMapping("/final/culmination/pending")
    public Iterable<ProposalInformationProjection> getCulminationPending(){
        return graduateWorkService.getCulminationPending();
    }

    @PostMapping("/final/defense/note")
    public Boolean setDefenseNote(@RequestBody SetDefenseNoteRequest setDefenseNoteRequest){
        System.out.println(setDefenseNoteRequest);
        return graduateWorkService.setDefenseNote(setDefenseNoteRequest);
                //return ResponseEntity.ok(new MessageResponse("Nota cargada exitosamente"));
    }


    @GetMapping("/proposals/reviewers")
    public Iterable<ProposalInformationProjection> getReviewersProposals(){
        return graduateWorkService.getReviewersProposals();
    }

    @GetMapping("/proposals/reviewers/pending")
    public Iterable<ProposalInformationProjection> getReviewersPending(){
        return graduateWorkService.getReviewersPending();
    }

    @GetMapping("/council/pending")
    public Iterable<ProposalInformationProjection> getCouncilPending(){
        return graduateWorkService.getCouncilPending();
    }

    @PutMapping("/proposals/reviewers")
    public GraduateWork assignReviewer(@RequestBody AssignReviewerRequest assignReviewerRequest){
        return graduateWorkService.assignReviewer(assignReviewerRequest.getGraduateWorkId(), assignReviewerRequest.getGraduateWorkCommittee(), assignReviewerRequest.getGraduateWorkReviewer());
    }

    @PutMapping("/council")
    public GraduateWork setSchoolCouncil(@RequestBody SetSchoolCouncilRequest setSchoolCouncilRequest){
        return graduateWorkService.setSchoolCouncil(setSchoolCouncilRequest.getGraduateWorkId(),setSchoolCouncilRequest.getGraduateWorkSchoolCouncil(),setSchoolCouncilRequest.getGraduateWorkAcademicTutor());
    }

    @PostMapping("/professor/tutor/graduatework")
    public Iterable<ProposalInformationProjection> getAcademicTutorGraduateWorks(@RequestBody ProfessorRequest professorRequest){
        return graduateWorkService.getAcademicTutorGraduateWorks(professorRequest.getProfessorDNI());
    }

    @PostMapping("/professor/tutor/graduatework/revision/count")
    public ResponseEntity<Integer> getAcademicTutorGraduateWorksCount(@RequestBody VerifyAcademicTutorRevision verifyAcademicTutorRevision){
        return ResponseEntity.ok(graduateWorkService.getAcademicTutorGraduateWorksCount(verifyAcademicTutorRevision.getGraduateWorkId(),verifyAcademicTutorRevision.getProfessorDNI()));
    }

    @PostMapping("/professor/tutor/graduatework/revision/approve")
    public ResponseEntity<Boolean> approveAcademicTutorRevision(@RequestBody VerifyAcademicTutorRevision verifyAcademicTutorRevision){
        return ResponseEntity.ok(graduateWorkService.approveAcademicTutorRevision(verifyAcademicTutorRevision.getProfessorDNI(),verifyAcademicTutorRevision.getGraduateWorkId()));
    }

    @GetMapping("/students/{id}")
    public GraduateWorkStudentProjection getGraduateWorkStudents(@PathVariable String id){
        return graduateWorkService.getGraduateWorkStudents(id);
    }

    @GetMapping("/reviewer/count/{id}")
    public ResponseEntity<Integer> getReviewerCount(@PathVariable("id") String id){
        return ResponseEntity.ok(graduateWorkService.getReviewerCount(id));
    }
    @GetMapping("/remaining/days/{id}")
    public Integer getRemainingDays(@PathVariable String id){
        return graduateWorkService.getRemainingDays(id);
    }

    @GetMapping("/jury/pending")
    public Iterable<ProposalInformationProjection> getJuryPending(){
        return graduateWorkService.getJuryPending();
    }

    @PostMapping("create/jury")
    public ResponseEntity<Boolean> createJury(@RequestBody CreateJuryRequest createJuryRequest){
        System.out.println(createJuryRequest);
            return ResponseEntity.ok(graduateWorkService.createJury(createJuryRequest.getProfessorDNI(), createJuryRequest.getSchoolCouncilId(),createJuryRequest.getGraduateWorkId(),createJuryRequest.getJuryType()));
    }

    @GetMapping("defense/pending")
    public Iterable<ProposalInformationProjection> getDefensePending(){
        return graduateWorkService.getDefensePending();
    }

    @PostMapping("coordinator/proposal/evaluation/approve")
    public ResponseEntity<Boolean> approveCoordinatorEvaluation(@RequestBody CreateApproveCoordinatorRequest createApproveCoordinatorRequest){
        return ResponseEntity.ok(graduateWorkService.approveCoordinatorEvaluation(createApproveCoordinatorRequest));
    }

    @PostMapping("coordinator/proposal/evaluation")
    public ResponseEntity<Boolean> createCoordinatorEvaluation(@RequestBody CreateCoordinatorRequest createCoordinatorRequest){
        return ResponseEntity.ok(graduateWorkService.createCoordinatorEvaluation(createCoordinatorRequest));
    }

    @PostMapping("coordinator/proposal/evaluation/create")
    public ResponseEntity<Boolean> generateCoordinatorEvaluation(@RequestBody CreateCoordinatorRequest createCoordinatorRequest){
        return ResponseEntity.ok(graduateWorkService.generateCoordinatorEvaluation(createCoordinatorRequest));
    }

    @PostMapping("coordinator/proposal/evaluation/approve/last")
    public ResponseEntity<Boolean> approveLastCoordinatorEvaluation(@RequestBody CreateCoordinatorRequest createCoordinatorRequest){
        return ResponseEntity.ok(graduateWorkService.approveLastCoordinatorEvaluation(createCoordinatorRequest));
    }

    @GetMapping("coordinator/proposal/evaluation/status/{id}")
    public ResponseEntity<Boolean> getCoordinatorEvaluationStatus(@PathVariable String id){
        return ResponseEntity.ok(graduateWorkService.getCoordinatorEvaluationStatus(id));
    }

    @PostMapping("coordinator/proposal/evaluation/status/approve")
    public ResponseEntity<Integer> aprobarRevisionCoordinador(@RequestBody ApproveCoordinatorEvaluationRequest approveCoordinatorEvaluationRequest){
        return ResponseEntity.ok(graduateWorkService.aprobarRevisionCoordinador(approveCoordinatorEvaluationRequest.getProfessorDNI(),approveCoordinatorEvaluationRequest.getGraduateWorkId()));
    }

    @PostMapping("create/reviewer/evaluation")
    public ResponseEntity<Boolean> createReviewerEvaluation (@RequestBody CreateReviewerEvaluationRequest createReviewerEvaluationRequest){
        return ResponseEntity.ok(graduateWorkService.createReviewerEvaluation(createReviewerEvaluationRequest.getProfessorDNI(),createReviewerEvaluationRequest.getGraduateWorkId(),createReviewerEvaluationRequest.getCommitteeId()));
    }

    @PostMapping("create/reviewer/evaluation/criteria")
    public ResponseEntity<Boolean> addCriteriaToReviewerEvaluation (@RequestBody CreateReviewerEvaluationCriteriaRequest createReviewerEvaluationCriteriaRequest){
        return ResponseEntity.ok(graduateWorkService.addCriteriaToReviewerEvaluation(createReviewerEvaluationCriteriaRequest.getProfessorDNI(),createReviewerEvaluationCriteriaRequest.getGraduateWorkId(),createReviewerEvaluationCriteriaRequest.getReviewerCriteriaId()));
    }

    @PostMapping("list/reviewer/evaluation/criteria")
    public Iterable<GetReviewerEvaluationCriteriaProjection> getReviewerEvaluationCriteria (@RequestBody GetReviewerEvaluationCriteria getReviewerEvaluationCriteria){
        return graduateWorkService.getReviewerEvaluationCriteria(getReviewerEvaluationCriteria.getProfessorDNI(),getReviewerEvaluationCriteria.getGraduateWorkId());
    }

    @PostMapping("/reviewer")
    public GetGraduateWorkReviewerProjection getGraduateWorkReviewer(@RequestBody GetGraduateWorkReviewer getGraduateWorkReviewer){
        return graduateWorkService.getGraduateWorkReviewer(getGraduateWorkReviewer.getGraduateWorkId());
    }
    /**/
    @PostMapping("/reviewer/evaluation/approve")
    public ResponseEntity<Boolean> approveReviewerEvaluation (@RequestBody ApproveCoordinatorEvaluationRequest approveCoordinatorEvaluationRequest){
        return ResponseEntity.ok(graduateWorkService.approveReviewerEvaluation(approveCoordinatorEvaluationRequest.getProfessorDNI(),approveCoordinatorEvaluationRequest.getGraduateWorkId(),approveCoordinatorEvaluationRequest.getComments()));
    }

    @PostMapping("/reviewer/evaluation/reprove")
    public ResponseEntity<Boolean> reproveReviewerEvaluation (@RequestBody ApproveCoordinatorEvaluationRequest approveCoordinatorEvaluationRequest){
        return ResponseEntity.ok(graduateWorkService.reproveReviewerEvaluation(approveCoordinatorEvaluationRequest.getProfessorDNI(),approveCoordinatorEvaluationRequest.getGraduateWorkId(),approveCoordinatorEvaluationRequest.getComments()));
    }

    @PostMapping("/reviewer/evaluation/criteria/approve")
    public ResponseEntity<Boolean> approveReviewerEvaluationCriteria (@RequestBody SetReviewerEvaluationCriteria setReviewerEvaluationCriteria ){
        return ResponseEntity.ok(graduateWorkService.approveReviewerEvaluationCriteria(setReviewerEvaluationCriteria.getProfessorDNI(),setReviewerEvaluationCriteria.getGraduateWorkId(),setReviewerEvaluationCriteria.getReviewerCriteriaId()));
    }

    @PostMapping("/reviewer/evaluation/criteria/reprove")
    public ResponseEntity<Boolean> reproveReviewerEvaluationCriteria (@RequestBody SetReviewerEvaluationCriteria setReviewerEvaluationCriteria){
        return ResponseEntity.ok(graduateWorkService.reproveReviewerEvaluationCriteria(setReviewerEvaluationCriteria.getProfessorDNI(),setReviewerEvaluationCriteria.getGraduateWorkId(),setReviewerEvaluationCriteria.getReviewerCriteriaId()));
    }

    @PostMapping("verify/tutor/revision/pending")
    public  ResponseEntity<Boolean> verifyAcademicTutorRevisionPending (@RequestBody VerifyAcademicTutorRevision verifyAcademicTutorRevision){
        System.out.println(verifyAcademicTutorRevision);
        return ResponseEntity.ok(graduateWorkService.verifyAcademicTutorRevisionPending(verifyAcademicTutorRevision.getProfessorDNI(), verifyAcademicTutorRevision.getGraduateWorkId()));
    }

    @PostMapping("add/tutor/revision")
    public  ResponseEntity<Boolean> addAcademicTutorEvaluation (@RequestBody VerifyAcademicTutorRevision verifyAcademicTutorRevision){
        return ResponseEntity.ok(graduateWorkService.addAcademicTutorEvaluation(verifyAcademicTutorRevision.getProfessorDNI(), verifyAcademicTutorRevision.getGraduateWorkId()));
    }

    @PostMapping("/tutor/revision/count")
    public  ResponseEntity<Integer> getAcademicTutorRevisionCount (@RequestBody VerifyAcademicTutorRevision verifyAcademicTutorRevision){
        return ResponseEntity.ok(graduateWorkService.getAcademicTutorRevisionCount(verifyAcademicTutorRevision.getGraduateWorkId(), verifyAcademicTutorRevision.getProfessorDNI()));
    }

    @PostMapping("/juries/ispresent")
    public  ResponseEntity<Boolean> getIsJuryPresent (@RequestBody GetJuryDataRequest getJuryDataRequest){
        return ResponseEntity.ok(graduateWorkService.getIsJuryPresent(getJuryDataRequest.getJuryDNI(), getJuryDataRequest.getGraduateWorkId()));
    }

    @PostMapping("/juries/designate/president")
    public  ResponseEntity<Boolean> designateJuryPresident (@RequestBody GetJuryDataRequest getJuryDataRequest){
        return ResponseEntity.ok(graduateWorkService.designateJuryPresident(getJuryDataRequest.getJuryDNI(), getJuryDataRequest.getGraduateWorkId()));
    }

    @PostMapping("/juries/ispresent/set")
    public  ResponseEntity<Boolean> setIsJuryPresent (@RequestBody GetJuryDataRequest getJuryDataRequest){
        return ResponseEntity.ok(graduateWorkService.setIsJuryPresent(getJuryDataRequest.getJuryDNI(), getJuryDataRequest.getGraduateWorkId(),getJuryDataRequest.getIsPresent()));
    }

    @GetMapping("/juries/report/criteria/experimental")
    public ResponseEntity<List<JuryReportExperimentalCriteriaProjection>> getJuryReportExperimentalCriteria (){
        return ResponseEntity.ok(graduateWorkService.getJuryReportExperimentalCriteria());
    }

    @GetMapping("/juries/report/seccion/experimental")
    public ResponseEntity<List<JuryReportExperimentalSeccionProjection>> getJuryReportExperimentalSeccion (){
        return ResponseEntity.ok(graduateWorkService.getJuryReportExperimentalSeccion());
    }

    @GetMapping("/juries/oral/criteria/experimental")
    public ResponseEntity<List<JuryReportExperimentalCriteriaProjection>> getJuryOralExperimentalCriteria (){
        return ResponseEntity.ok(graduateWorkService.getJuryOralExperimentalCriteria());
    }

    @GetMapping("/juries/oral/seccion/experimental")
    public ResponseEntity<List<JuryReportExperimentalSeccionProjection>> getJuryOralExperimentalSeccion (){
        return ResponseEntity.ok(graduateWorkService.getJuryOralExperimentalSeccion());
    }

    @GetMapping("/juries/tutor/report/criteria/experimental")
    public ResponseEntity<List<JuryReportExperimentalCriteriaProjection>> getTutorReportExperimentalCriteria (){
        return ResponseEntity.ok(graduateWorkService.getTutorReportExperimentalCriteria());
    }

    @GetMapping("/juries/tutor/oral/criteria/experimental")
    public ResponseEntity<List<JuryReportExperimentalCriteriaProjection>> getTutorOralExperimentalCriteria (){
        return ResponseEntity.ok(graduateWorkService.getTutorOralExperimentalCriteria());
    }

    @PostMapping("/juries/data")
    public ResponseEntity<GetJuryDataProjection> getJuryData (@RequestBody GetJuryDataRequest getJuryDataRequest){
        return ResponseEntity.ok(graduateWorkService.getJuryData(getJuryDataRequest.getJuryDNI(),getJuryDataRequest.getGraduateWorkId()));
    }

    @PostMapping("/juries/principal/set/report/note")
    public ResponseEntity<Boolean> setJuryReportFinalNote (@RequestBody SetJuryFinalNote setJuryFinalNote){
        System.out.println(setJuryFinalNote);
        return ResponseEntity.ok(graduateWorkService.setJuryReportFinalNote(setJuryFinalNote.getJuryDNI(),setJuryFinalNote.getStudentDNI(),setJuryFinalNote.getGraduateWorkId(),setJuryFinalNote.getEvaluationNote()));
    }

    @PostMapping("/juries/principal/set/oral/note")
    public ResponseEntity<Boolean> setJuryOralFinalNote (@RequestBody SetJuryFinalNote setJuryFinalNote){
        System.out.println(setJuryFinalNote);
        return ResponseEntity.ok(graduateWorkService.setJuryOralFinalNote(setJuryFinalNote.getJuryDNI(),setJuryFinalNote.getStudentDNI(),setJuryFinalNote.getGraduateWorkId(),setJuryFinalNote.getEvaluationNote()));
    }

    @PostMapping("/juries/tutor/set/oral/note")
    public ResponseEntity<Boolean> setTutorReportFinalNote (@RequestBody SetJuryFinalNote setJuryFinalNote){
        System.out.println(setJuryFinalNote);
        return ResponseEntity.ok(graduateWorkService.setTutorReportFinalNote(setJuryFinalNote.getJuryDNI(),setJuryFinalNote.getStudentDNI(),setJuryFinalNote.getGraduateWorkId(),setJuryFinalNote.getEvaluationNote()));
    }

    @PostMapping("/juries/tutor/set/report/note")
    public ResponseEntity<Boolean> setTutorOralFinalNote (@RequestBody SetJuryFinalNote setJuryFinalNote){
        System.out.println(setJuryFinalNote);
        return ResponseEntity.ok(graduateWorkService.setTutorOralFinalNote(setJuryFinalNote.getJuryDNI(),setJuryFinalNote.getStudentDNI(),setJuryFinalNote.getGraduateWorkId(),setJuryFinalNote.getEvaluationNote()));
    }

    @PostMapping("/juries/tutor/get/submmitted")
    public ResponseEntity<Boolean> getHasTutorSubmittedFinalNote (@RequestBody SetJuryFinalNote setJuryFinalNote){
        System.out.println(setJuryFinalNote);
        return ResponseEntity.ok(graduateWorkService.getHasTutorSubmittedFinalNote(setJuryFinalNote.getJuryDNI(),setJuryFinalNote.getGraduateWorkId()));
    }

    @PostMapping("/juries/jury/get/submmitted")
    public ResponseEntity<Boolean> getHasJurySubmittedFinalNote (@RequestBody SetJuryFinalNote setJuryFinalNote){
        System.out.println(setJuryFinalNote);
        return ResponseEntity.ok(graduateWorkService.getHasJurySubmittedFinalNote(setJuryFinalNote.getJuryDNI(),setJuryFinalNote.getGraduateWorkId()));
    }

    @PostMapping("/juries/jury/validate/president")
    public ResponseEntity<Boolean> getHasJuryPresident (@RequestBody SetJuryFinalNote setJuryFinalNote){
        System.out.println(setJuryFinalNote);
        return ResponseEntity.ok(graduateWorkService.getHasJuryPresident(setJuryFinalNote.getGraduateWorkId()));
    }

    @PostMapping("/juries/jury/validate/all")
    public ResponseEntity<Boolean> getAllNotesValidation (@RequestBody SetJuryFinalNote setJuryFinalNote){
        System.out.println(setJuryFinalNote);
        return ResponseEntity.ok(graduateWorkService.getAllNotesValidation(setJuryFinalNote.getGraduateWorkId()));
    }

    @PostMapping("/juries/jury/get/oral/note")
    public ResponseEntity<Integer> getJuryOralNote (@RequestBody SetJuryFinalNote setJuryFinalNote){
        System.out.println(setJuryFinalNote);
        return ResponseEntity.ok(graduateWorkService.getJuryOralNote(setJuryFinalNote.getGraduateWorkId(),setJuryFinalNote.getJuryDNI()));
    }

    @PostMapping("/juries/jury/get/report/note")
    public ResponseEntity<Integer> getJuryReportNote (@RequestBody SetJuryFinalNote setJuryFinalNote){
        System.out.println(setJuryFinalNote);
        return ResponseEntity.ok(graduateWorkService.getJuryReportNote(setJuryFinalNote.getGraduateWorkId(),setJuryFinalNote.getJuryDNI()));
    }

    @PostMapping("/juries/tutor/get/oral/note")
    public ResponseEntity<Integer> getTutorOralNote (@RequestBody SetJuryFinalNote setJuryFinalNote){
        System.out.println(setJuryFinalNote);
        return ResponseEntity.ok(graduateWorkService.getTutorOralNote(setJuryFinalNote.getGraduateWorkId(),setJuryFinalNote.getJuryDNI()));
    }

    @PostMapping("/juries/tutor/get/report/note")
    public ResponseEntity<Integer> getTutorReportNote (@RequestBody SetJuryFinalNote setJuryFinalNote){
        System.out.println(setJuryFinalNote);
        return ResponseEntity.ok(graduateWorkService.getTutorReportNote(setJuryFinalNote.getGraduateWorkId(),setJuryFinalNote.getJuryDNI()));
    }

    @PostMapping("/juries/president/get/oral/note")
    public ResponseEntity<Integer> getPresidentOralNote (@RequestBody SetJuryFinalNote setJuryFinalNote){
        System.out.println(setJuryFinalNote);
        return ResponseEntity.ok(graduateWorkService.getPresidentOralNote(setJuryFinalNote.getGraduateWorkId(),setJuryFinalNote.getJuryDNI()));
    }

    @PostMapping("/juries/president/get/report/note")
    public ResponseEntity<Integer> getPresidentReportNote (@RequestBody SetJuryFinalNote setJuryFinalNote){
        System.out.println(setJuryFinalNote);
        return ResponseEntity.ok(graduateWorkService.getPresidentReportNote(setJuryFinalNote.getGraduateWorkId(),setJuryFinalNote.getJuryDNI()));
    }

    @PostMapping("/juries/get")
    public ResponseEntity<List<GetJuryDataProjection>> getGraduateWorkJuries (@RequestBody SetJuryFinalNote setJuryFinalNote){
        System.out.println(setJuryFinalNote);
        return ResponseEntity.ok(graduateWorkService.getGraduateWorkJuries(setJuryFinalNote.getGraduateWorkId()));
    }

    @PostMapping("/juries/jury/add/criteria/note/oral")
    public ResponseEntity<Boolean> addJuryOralEvaluationNote (@RequestBody AddJuryEvaluationNote addJuryEvaluationNote){
        return ResponseEntity.ok(graduateWorkService.addJuryOralEvaluationNote(addJuryEvaluationNote.getJuryDNI(),addJuryEvaluationNote.getStudentDNI(),addJuryEvaluationNote.getGraduateWorkId(),addJuryEvaluationNote.getCriteriaId(),addJuryEvaluationNote.getEvaluationNote()));
    }

    @PostMapping("/juries/jury/add/criteria/note/report")
    public ResponseEntity<Boolean> addJuryReportEvaluationNote (@RequestBody AddJuryEvaluationNote addJuryEvaluationNote){
        return ResponseEntity.ok(graduateWorkService.addJuryReportEvaluationNote(addJuryEvaluationNote.getJuryDNI(),addJuryEvaluationNote.getStudentDNI(),addJuryEvaluationNote.getGraduateWorkId(),addJuryEvaluationNote.getCriteriaId(),addJuryEvaluationNote.getEvaluationNote()));
    }

    @PostMapping("/juries/tutor/add/criteria/note/report")
    public ResponseEntity<Boolean> addTutorReportEvaluationNote (@RequestBody AddJuryEvaluationNote addJuryEvaluationNote){
        return ResponseEntity.ok(graduateWorkService.addTutorReportEvaluationNote(addJuryEvaluationNote.getJuryDNI(),addJuryEvaluationNote.getStudentDNI(),addJuryEvaluationNote.getGraduateWorkId(),addJuryEvaluationNote.getCriteriaId(),addJuryEvaluationNote.getEvaluationNote()));
    }

    @PostMapping("/juries/tutor/add/criteria/note/oral")
    public ResponseEntity<Boolean> addTutorOralEvaluationNote (@RequestBody AddJuryEvaluationNote addJuryEvaluationNote){
        return ResponseEntity.ok(graduateWorkService.addTutorOralEvaluationNote(addJuryEvaluationNote.getJuryDNI(),addJuryEvaluationNote.getStudentDNI(),addJuryEvaluationNote.getGraduateWorkId(),addJuryEvaluationNote.getCriteriaId(),addJuryEvaluationNote.getEvaluationNote()));
    }

    @PostMapping("/juries/tutor/get/criteria/note/oral")
    public ResponseEntity<List<GetJuryEvaluationNoteProjection>> getTutorOralEvaluationNote (@RequestBody AddJuryEvaluationNote addJuryEvaluationNote){
        return ResponseEntity.ok(graduateWorkService.getTutorOralEvaluationNote(addJuryEvaluationNote.getJuryDNI(),addJuryEvaluationNote.getStudentDNI(),addJuryEvaluationNote.getGraduateWorkId()));
    }

    @PostMapping("/juries/tutor/get/criteria/note/report")
    public ResponseEntity<List<GetJuryEvaluationNoteProjection>> getTutorReportEvaluationNote (@RequestBody AddJuryEvaluationNote addJuryEvaluationNote){
        return ResponseEntity.ok(graduateWorkService.getTutorReportEvaluationNote(addJuryEvaluationNote.getJuryDNI(),addJuryEvaluationNote.getStudentDNI(),addJuryEvaluationNote.getGraduateWorkId()));
    }

    @PostMapping("/juries/jury/get/criteria/note/oral")
    public ResponseEntity<List<GetJuryEvaluationNoteProjection>> getJuryOralEvaluationNote (@RequestBody AddJuryEvaluationNote addJuryEvaluationNote){
        return ResponseEntity.ok(graduateWorkService.getJuryOralEvaluationNote(addJuryEvaluationNote.getJuryDNI(),addJuryEvaluationNote.getStudentDNI(),addJuryEvaluationNote.getGraduateWorkId()));
    }

    @PostMapping("/juries/jury/get/criteria/note/report")
    public ResponseEntity<List<GetJuryEvaluationNoteProjection>> getJuryReportEvaluationNote (@RequestBody AddJuryEvaluationNote addJuryEvaluationNote){
        return ResponseEntity.ok(graduateWorkService.getJuryReportEvaluationNote(addJuryEvaluationNote.getJuryDNI(),addJuryEvaluationNote.getStudentDNI(), addJuryEvaluationNote.getGraduateWorkId()));
    }

    @PostMapping("/juries/jury/get/data")
    public ResponseEntity<GetJuryDataProjection> getGraduateWorkJuryByRol (@RequestBody GetJuryDataRequest getJuryDataRequest){
        return ResponseEntity.ok(graduateWorkService.getGraduateWorkJuryByRol(getJuryDataRequest.getGraduateWorkId(),getJuryDataRequest.getJuryType()));
    }

    @PutMapping("/juries/tutor/get/criteria/note/oral")
    public ResponseEntity<Boolean> changeTutorOralEvaluationNote (@RequestBody AddJuryEvaluationNote addJuryEvaluationNote){
        return ResponseEntity.ok(graduateWorkService.changeTutorOralEvaluationNote(addJuryEvaluationNote.getJuryDNI(),addJuryEvaluationNote.getStudentDNI(),addJuryEvaluationNote.getGraduateWorkId(),addJuryEvaluationNote.getCriteriaId(),addJuryEvaluationNote.getEvaluationNote()));
    }

    @PutMapping("/juries/tutor/get/criteria/note/report")
    public ResponseEntity<Boolean> changeTutorReportEvaluationNote (@RequestBody AddJuryEvaluationNote addJuryEvaluationNote){
        return ResponseEntity.ok(graduateWorkService.changeTutorReportEvaluationNote(addJuryEvaluationNote.getJuryDNI(),addJuryEvaluationNote.getStudentDNI(),addJuryEvaluationNote.getGraduateWorkId(),addJuryEvaluationNote.getCriteriaId(),addJuryEvaluationNote.getEvaluationNote()));
    }

    @PutMapping("/juries/jury/get/criteria/note/oral")
    public ResponseEntity<Boolean> changeJuryOralEvaluationNote (@RequestBody AddJuryEvaluationNote addJuryEvaluationNote){
        return ResponseEntity.ok(graduateWorkService.changeJuryOralEvaluationNote(addJuryEvaluationNote.getJuryDNI(),addJuryEvaluationNote.getStudentDNI(),addJuryEvaluationNote.getGraduateWorkId(),addJuryEvaluationNote.getCriteriaId(),addJuryEvaluationNote.getEvaluationNote()));
    }

    @PutMapping("/juries/jury/get/criteria/note/report")
    public ResponseEntity<Boolean> changeJuryReportEvaluationNote (@RequestBody AddJuryEvaluationNote addJuryEvaluationNote){
        return ResponseEntity.ok(graduateWorkService.changeJuryReportEvaluationNote(addJuryEvaluationNote.getJuryDNI(),addJuryEvaluationNote.getStudentDNI(), addJuryEvaluationNote.getGraduateWorkId(),addJuryEvaluationNote.getCriteriaId(),addJuryEvaluationNote.getEvaluationNote()));
    }
    @PostMapping("/final/note/charge")
    public ResponseEntity<Boolean> chargeFinalNote (@RequestBody ChargeFinalNoteRequest chargeFinalNoteRequest){
        return ResponseEntity.ok(graduateWorkService.chargeFinalNote( chargeFinalNoteRequest.getGraduateWorkId(),chargeFinalNoteRequest.getFinalNote(),chargeFinalNoteRequest.getMention()));
    }

    @GetMapping("/culminated/{id}")
    public ResponseEntity<Boolean> isCulminated (@PathVariable String id){
        return ResponseEntity.ok(graduateWorkService.isCulminated( id ));
    }

}

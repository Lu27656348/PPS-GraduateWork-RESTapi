package com.example.demo.controller;

import com.example.demo.entity.GraduateWork;
import com.example.demo.interfaces.*;

import com.example.demo.interfaces.projections.GetReviewerEvaluationCriteriaProjection;
import com.example.demo.interfaces.requests.*;
import com.example.demo.service.GraduateWorkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return graduateWorkService.setSchoolCouncil(setSchoolCouncilRequest.getGraduateWorkId(),setSchoolCouncilRequest.getGraduateWorkSchoolCouncil());
    }

    @PostMapping("/professor/tutor/graduatework")
    public Iterable<ProposalInformationProjection> getAcademicTutorGraduateWorks(@RequestBody ProfessorRequest professorRequest){
        return graduateWorkService.getAcademicTutorGraduateWorks(professorRequest.getProfessorDNI());
    }

    @GetMapping("/students/{id}")
    public GraduateWorkStudentProjection getGraduateWorkStudents(@PathVariable String id){
        return graduateWorkService.getGraduateWorkStudents(id);
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
    public CreateJuryProjection createJury(@RequestBody CreateJuryRequest createJuryRequest){
            return graduateWorkService.createJury(createJuryRequest.getGraduateWorkId(), createJuryRequest.getProfessorDNI(),createJuryRequest.getJuryType());
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
        return ResponseEntity.ok(graduateWorkService.createReviewerEvaluation(createReviewerEvaluationRequest.getProfessorDNI(),createReviewerEvaluationRequest.getGraduateWorkId()));
    }

    @PostMapping("create/reviewer/evaluation/criteria")
    public ResponseEntity<Boolean> addCriteriaToReviewerEvaluation (@RequestBody CreateReviewerEvaluationCriteriaRequest createReviewerEvaluationCriteriaRequest){
        return ResponseEntity.ok(graduateWorkService.addCriteriaToReviewerEvaluation(createReviewerEvaluationCriteriaRequest.getProfessorDNI(),createReviewerEvaluationCriteriaRequest.getGraduateWorkId(),createReviewerEvaluationCriteriaRequest.getReviewerCriteriaId()));
    }

    @PostMapping("list/reviewer/evaluation/criteria")
    public Iterable<GetReviewerEvaluationCriteriaProjection> getReviewerEvaluationCriteria (@RequestBody GetReviewerEvaluationCriteria getReviewerEvaluationCriteria){
        return graduateWorkService.getReviewerEvaluationCriteria(getReviewerEvaluationCriteria.getProfessorDNI(),getReviewerEvaluationCriteria.getGraduateWorkId());
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

}



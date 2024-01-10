package com.example.demo.controller;

import com.example.demo.entity.GraduateWork;
import com.example.demo.interfaces.*;

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
}



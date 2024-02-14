package com.example.demo.controller;

import com.example.demo.entity.SchoolCouncils;
import com.example.demo.interfaces.MessageResponse;
import com.example.demo.service.SchoolCouncilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/councils")
public class SchoolCouncilController {
    private final SchoolCouncilService schoolCouncilService;

    public SchoolCouncilController(SchoolCouncilService schoolCouncilService) {
        this.schoolCouncilService = schoolCouncilService;
    }

    @GetMapping
    public Iterable<SchoolCouncils> getAllSchoolCouncils(){
        return schoolCouncilService.getAllSchoolCouncils();
    }

    @GetMapping("{id}")
    public SchoolCouncils getSchoolCouncilById(@PathVariable String id){
        return schoolCouncilService.getSchoolCouncilById(id);
    }
    @PostMapping
    public SchoolCouncils createSchoolCouncil(@RequestBody SchoolCouncils schoolCouncil){
        return schoolCouncilService.createSchoolCouncil(schoolCouncil);
    }

    @PutMapping("/{id}")
    public SchoolCouncils updateSchoolCouncil(@PathVariable String id, @RequestBody SchoolCouncils schoolCouncil){
        return schoolCouncilService.updateSchoolCouncil(id,schoolCouncil);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteSchoolCouncilById(@PathVariable String id){
        schoolCouncilService.deleteSchoolCouncilById(id);
        return ResponseEntity.ok(new MessageResponse("Consejo de Escuela eliminado exitosamente"));
    }

}

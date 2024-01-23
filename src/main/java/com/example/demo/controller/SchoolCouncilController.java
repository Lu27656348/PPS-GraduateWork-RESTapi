package com.example.demo.controller;

import com.example.demo.entity.SchoolCouncils;
import com.example.demo.service.SchoolCouncilService;
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

    @PostMapping
    public SchoolCouncils createSchoolCouncil(@RequestBody SchoolCouncils schoolCouncil){
        return schoolCouncilService.createSchoolCouncil(schoolCouncil);
    }
}

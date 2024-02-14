package com.example.demo.controller;

import com.example.demo.entity.Professor;
import com.example.demo.entity.User;
import com.example.demo.interfaces.ProfessorResponseProjection;
import com.example.demo.responses.ProfessorResponse;
import com.example.demo.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professors")
public class ProfessorController {
    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public Professor createProfessor (@RequestBody Professor professor){
        return professorService.createProfessor(professor);
    }

    @GetMapping
    public Iterable<Professor> getAllProfessors (){
        return  professorService.getAllProfessors();
    }

    @GetMapping("{id}")
    public Professor getProfessorById (@PathVariable String id){
        return professorService.getProfessorById(id);

    }
    @PutMapping("{id}")
    public Professor updateProfessor (@PathVariable String id, @RequestBody Professor professor){
        return professorService.updateProfessor(id,professor);
    }
    @DeleteMapping("{id}")
    public void deleteProfessor (@PathVariable String id){
        professorService.deleteProfessorById(id);
    }

    @GetMapping("data")
    public Iterable<ProfessorResponseProjection> getProfessorsData(){
        return professorService.getProfessorsData();
    }

    @GetMapping("data/all")
    public Iterable<Professor> getProfessorsDataAll(){
        return professorService.getAllProfessors();
    }
}

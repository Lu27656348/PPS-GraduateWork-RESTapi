package com.example.demo.controller;

import com.example.demo.entity.Criteria;
import com.example.demo.service.CriteriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/criteria")
public class CriteriaController {
    private final CriteriaService criteriaService;

    public CriteriaController(CriteriaService criteriaService) {
        this.criteriaService = criteriaService;
    }

    @GetMapping
    public Iterable<Criteria> getAllCriteria(){
        return criteriaService.getAllCriteria();
    }

    @PostMapping
    public Criteria createCriteria(@RequestBody  Criteria criteria) {
        return criteriaService.createCriteria(criteria);
    }

    @PutMapping
    public Criteria updateCriteria(@RequestBody Criteria criteria) {
        return criteriaService.updateCriteria(criteria);
    }

    @GetMapping("/escuela/{schoolname}/modelo/{criteriamodel}")
    public List<Criteria> getCriteriaByModelAndSchool(@PathVariable String schoolname,@PathVariable String criteriamodel) {
        return criteriaService.getCriteriaByModelAndSchool(schoolname,criteriamodel);
    }


}

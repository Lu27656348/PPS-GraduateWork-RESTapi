package com.example.demo.controller;

import com.example.demo.entity.Criteria;
import com.example.demo.service.CriteriaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

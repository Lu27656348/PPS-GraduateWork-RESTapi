package com.example.demo.service;

import com.example.demo.entity.Criteria;
import com.example.demo.repository.CriteriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CriteriaService {

    private final CriteriaRepository criteriaRepository;

    public CriteriaService(CriteriaRepository criteriaRepository) {
        this.criteriaRepository = criteriaRepository;
    }

    public Iterable<Criteria> getAllCriteria(){
        return criteriaRepository.findAll();
    }
}

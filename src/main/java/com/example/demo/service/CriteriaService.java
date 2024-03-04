package com.example.demo.service;

import com.example.demo.entity.Criteria;
import com.example.demo.repository.CriteriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CriteriaService {

    private final CriteriaRepository criteriaRepository;

    public CriteriaService(CriteriaRepository criteriaRepository) {
        this.criteriaRepository = criteriaRepository;
    }

    public Iterable<Criteria> getAllCriteria(){
        return criteriaRepository.findAll();
    }

    public Criteria createCriteria(Criteria criteria) {
        return criteriaRepository.save(criteria);
    }

    public Criteria updateCriteria(Criteria criteria) {
        Criteria search = criteriaRepository.findById(criteria.getReviewerCriteriaId()).orElse(null);
        if( search != null ){
            search.setReviewerCriteriaDescription(criteria.getReviewerCriteriaDescription());
            return criteriaRepository.save(search);
        }
        return null;

    }

    public List<Criteria> getCriteriaByModelAndSchool(String schoolname,String criteriamodel) {
        return criteriaRepository.getCriteriaByModelAndSchool(schoolname,criteriamodel);
    }
}

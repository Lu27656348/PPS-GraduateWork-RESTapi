package com.example.demo.service;

import com.example.demo.entity.criterios.CriterioTutorEmpresarial;
import com.example.demo.repository.CriterioTutorEmpresarialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriterioTutorEmpresarialService {
    private final CriterioTutorEmpresarialRepository criterioTutorEmpresarialRepository;

    public CriterioTutorEmpresarialService(CriterioTutorEmpresarialRepository criterioTutorEmpresarialRepository) {
        this.criterioTutorEmpresarialRepository = criterioTutorEmpresarialRepository;
    }

    public CriterioTutorEmpresarial createCriterioTutorEmpresarial(CriterioTutorEmpresarial criterioTutorEmpresarial){
        return criterioTutorEmpresarialRepository.save(criterioTutorEmpresarial);
    }

    public CriterioTutorEmpresarial updateCriterioTutorEmpresarial(CriterioTutorEmpresarial criterioTutorEmpresarial){
        CriterioTutorEmpresarial search = criterioTutorEmpresarialRepository.findById(criterioTutorEmpresarial.getCriteriaId()).orElse(null);
        if( search != null ){
            search.setCriteriaName(criterioTutorEmpresarial.getCriteriaName());
            search.setMaxNote(criterioTutorEmpresarial.getMaxNote());
            return criterioTutorEmpresarialRepository.save(search);
        }
        return null;
    }

    public List<CriterioTutorEmpresarial> getAllCriterioTutorEmpresarial(){
        return (List<CriterioTutorEmpresarial>) criterioTutorEmpresarialRepository.findAll();
    }

    public List<CriterioTutorEmpresarial> getCriterioTutorEmpresarialBySchool(String schoolName){
        return criterioTutorEmpresarialRepository.obtenerCriteriosTutorEmpresarialBySchool(schoolName);
    }


}

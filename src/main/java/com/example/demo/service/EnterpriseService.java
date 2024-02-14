package com.example.demo.service;

import com.example.demo.entity.Enterprises;
import com.example.demo.repository.EnterpriseRepository;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseService {
    private final EnterpriseRepository enterpriseRepository;

    public EnterpriseService(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    public Iterable<Enterprises> getAllEnterprises(){
        return enterpriseRepository.findAll();
    }

    public Enterprises getEnterpriseById (Integer id){
        return enterpriseRepository.findById(id).orElse(null);
    }

    public Enterprises createEnterprise(Enterprises enterprise){
        return enterpriseRepository.save(enterprise);
    }

    public Enterprises updateEnterprise(Integer id, Enterprises enterprise){
        Enterprises empresaSearch = enterpriseRepository.findById(id).orElse(null);
        if(empresaSearch != null ){
            empresaSearch.setEnterpriseAddress(enterprise.getEnterpriseAddress());
            empresaSearch.setEnterpriseDescription(enterprise.getEnterpriseDescription());
            empresaSearch.setEnterpriseName(enterprise.getEnterpriseName());
            return enterpriseRepository.save(empresaSearch);
        }
        return null;
    }

    public void deleteEnterpriseById(Integer id){
        enterpriseRepository.deleteById(id);
    }
}

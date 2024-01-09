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
}

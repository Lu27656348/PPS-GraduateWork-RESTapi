package com.example.demo.service;

import com.example.demo.entity.ExternalPersonnel;
import com.example.demo.repository.ExternalPersonnelRepository;
import org.springframework.stereotype.Service;

@Service
public class ExternalPersonnelService {
    private final ExternalPersonnelRepository externalPersonnelRepository;

    public ExternalPersonnelService(ExternalPersonnelRepository externalPersonnelRepository) {
        this.externalPersonnelRepository = externalPersonnelRepository;
    }

    public Iterable<ExternalPersonnel> getAllExternalPersonnel(){
        return externalPersonnelRepository.findAll();
    }

    public ExternalPersonnel createExternalPersonnel(ExternalPersonnel externalPersonnel){
        return externalPersonnelRepository.save(externalPersonnel);
    }

    public Iterable<ExternalPersonnel> getExternalPersonnelByEnterpriseId (Integer enterpriseId){
        return externalPersonnelRepository.getExternalPersonnelByEnterpriseId(enterpriseId);
    }


}

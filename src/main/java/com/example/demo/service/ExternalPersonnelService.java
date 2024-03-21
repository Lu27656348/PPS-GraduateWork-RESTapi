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


    public ExternalPersonnel getExternalPersonnelById(String id){
        return externalPersonnelRepository.findById(id).orElse(null);
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

    public ExternalPersonnel updateExternalPersonnel(ExternalPersonnel externalPersonnel){
        ExternalPersonnel externalSearch = externalPersonnelRepository.findById(externalPersonnel.getExternalPersonnelDNI()).orElse(null);
        if(externalSearch != null) {
            externalSearch.setExternalPersonnelProfession(
                    externalPersonnel.getExternalPersonnelProfession()
            );
            externalSearch.setExternalPersonnelAddress(
                    externalPersonnel.getExternalPersonnelAddress()
            );
            externalSearch.setExternalPersonnelEnterpriseId(
                    externalPersonnel.getExternalPersonnelEnterpriseId()
            );
            externalSearch.setExternalPersonnelOffice(
                    externalPersonnel.getExternalPersonnelOffice()
            );
            externalSearch.setExternalPersonnelWorkExperience(
                    externalPersonnel.getExternalPersonnelWorkExperience()
            );
            externalSearch.setExternalPersonnelGraduationYear(
                    externalPersonnel.getExternalPersonnelGraduationYear()
            );

            return externalPersonnelRepository.save(externalSearch);

        }
        return null;
    }
}

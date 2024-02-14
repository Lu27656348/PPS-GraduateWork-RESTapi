package com.example.demo.service;

import com.example.demo.entity.SchoolCouncils;
import com.example.demo.repository.SchoolCouncilRepository;
import org.springframework.stereotype.Service;

@Service
public class SchoolCouncilService {
    private final SchoolCouncilRepository schoolCouncilRepository;

    public SchoolCouncilService(SchoolCouncilRepository schoolCouncilRepository) {
        this.schoolCouncilRepository = schoolCouncilRepository;
    }

    public Iterable<SchoolCouncils> getAllSchoolCouncils(){
        return schoolCouncilRepository.findAll();
    }

    public SchoolCouncils getSchoolCouncilById( String id ){
        return schoolCouncilRepository.findById(id).orElse(null);
    }

    public SchoolCouncils createSchoolCouncil(SchoolCouncils schoolCouncil){
        return schoolCouncilRepository.save(schoolCouncil);
    }

    public void deleteSchoolCouncilById(String id){
         schoolCouncilRepository.deleteById(id);
    }

    public SchoolCouncils updateSchoolCouncil(String id, SchoolCouncils schoolCouncil){
        SchoolCouncils search = schoolCouncilRepository.findById(id).orElse(null);
        if( search != null ){
            search.setSchoolCouncilId(schoolCouncil.getSchoolCouncilId());
            search.setSchoolSchoolType(schoolCouncil.getSchoolSchoolType());
            search.setSchoolcouncildate(schoolCouncil.getSchoolcouncildate());
            return schoolCouncilRepository.save(search);
        }

        return null;
    }
}

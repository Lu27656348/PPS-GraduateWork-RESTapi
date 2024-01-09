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
}

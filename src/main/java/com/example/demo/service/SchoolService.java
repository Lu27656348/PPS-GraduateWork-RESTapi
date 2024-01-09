package com.example.demo.service;

import com.example.demo.entity.Schools;
import com.example.demo.repository.SchoolRepository;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public Iterable<Schools> getAllSchools(){
        return schoolRepository.findAll();
    }

}

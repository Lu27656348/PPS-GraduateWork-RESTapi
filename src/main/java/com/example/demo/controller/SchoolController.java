package com.example.demo.controller;

import com.example.demo.entity.Schools;
import com.example.demo.service.SchoolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("schools")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public Iterable<Schools> getAllSchools(){
        return schoolService.getAllSchools();
    }
}

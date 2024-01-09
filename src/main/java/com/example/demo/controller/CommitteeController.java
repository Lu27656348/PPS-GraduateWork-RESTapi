package com.example.demo.controller;

import com.example.demo.entity.Committee;
import com.example.demo.service.CommitteeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/committees")
public class CommitteeController {

    private final CommitteeService committeeService;

    public CommitteeController(CommitteeService committeeService) {
        this.committeeService = committeeService;
    }

    @GetMapping
    public Iterable<Committee> getAllCommittees(){
        return committeeService.getAllCommittees();
    }

}

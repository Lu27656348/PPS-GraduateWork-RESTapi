package com.example.demo.controller;

import com.example.demo.entity.Committee;
import com.example.demo.service.CommitteeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping
    public Committee createCommittee(@RequestBody Committee committee){
        return committeeService.createCommittee(committee);
    }

}

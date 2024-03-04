package com.example.demo.controller;

import com.example.demo.entity.Committee;
import com.example.demo.interfaces.MessageResponse;
import com.example.demo.service.CommitteeService;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/by/school/{schoolname}")
    public Iterable<Committee> getAllCommitteesBySchool(@PathVariable String schoolname) {
        return committeeService.getAllCommitteesBySchool(schoolname);
    }
    @PostMapping
    public Committee createCommittee(@RequestBody Committee committee){
        return committeeService.createCommittee(committee);
    }

    @PutMapping("/{id}")
    public Committee updateCommittee(@PathVariable String id, @RequestBody Committee committee){
        return committeeService.updateCommittee(id,committee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteCommittee(@PathVariable String id){
        committeeService.deleteCommittee(id);
        return ResponseEntity.ok(new MessageResponse("Comite eliminado exitosamente"));
    }

}

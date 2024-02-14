package com.example.demo.controller;

import com.example.demo.entity.ExternalPersonnel;
import com.example.demo.interfaces.MessageResponse;
import com.example.demo.interfaces.requests.CreateExternalPersonnelRequest;
import com.example.demo.service.ExternalPersonnelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/external")
public class ExternalPersonnelController {

    private final ExternalPersonnelService externalPersonnelService;

    public ExternalPersonnelController(ExternalPersonnelService externalPersonnelService) {
        this.externalPersonnelService = externalPersonnelService;
    }

    @GetMapping
    public Iterable<ExternalPersonnel> getAllExternalPersonnel(){
        return externalPersonnelService.getAllExternalPersonnel();
    }

    @GetMapping("/{id}")
    public ExternalPersonnel getExternalPersonnelById(@PathVariable String id){
        return externalPersonnelService.getExternalPersonnelById(id);
    }
    @GetMapping("enterprise/{id}")
    public Iterable<ExternalPersonnel> getExternalPersonnelByEnterpriseId(@PathVariable Integer id){
        return externalPersonnelService.getExternalPersonnelByEnterpriseId(id);
    }

    @PostMapping
    public ResponseEntity<ExternalPersonnel> createExternalPersonnel(@RequestBody ExternalPersonnel externalPersonnel){

        return ResponseEntity.ok(externalPersonnelService.createExternalPersonnel(externalPersonnel));

    }
}

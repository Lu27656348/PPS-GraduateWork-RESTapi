package com.example.demo.controller;

import com.example.demo.entity.Enterprises;
import com.example.demo.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enterprises")
public class EnterpriseController {

    private final EnterpriseService enterpriseService;

    @Autowired
    public EnterpriseController(EnterpriseService enterpriseService) {
        this.enterpriseService = enterpriseService;
    }

    @GetMapping
    public Iterable<Enterprises> getAllEnterprises(){
        return enterpriseService.getAllEnterprises();
    }

    @GetMapping("{id}")
    public Enterprises getEnterpriseById(@PathVariable Integer id){
        System.out.println(id);
        return enterpriseService.getEnterpriseById(id);
    }
}

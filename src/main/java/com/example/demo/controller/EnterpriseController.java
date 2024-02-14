package com.example.demo.controller;

import com.example.demo.entity.Enterprises;
import com.example.demo.interfaces.MessageResponse;
import com.example.demo.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Enterprises createEnterprise(@RequestBody Enterprises enterprise){
        return enterpriseService.createEnterprise(enterprise);
    }

    @PutMapping("{id}")
    public Enterprises updateEnterprise(@PathVariable Integer id, @RequestBody Enterprises enterprise){
        return enterpriseService.updateEnterprise(id,enterprise);
    }

    @DeleteMapping("{id}")
    public MessageResponse deleteEnterprise(@PathVariable Integer id){
        enterpriseService.deleteEnterpriseById(id);
        return new MessageResponse("Empresa eliminada exitosamente");
    }
}

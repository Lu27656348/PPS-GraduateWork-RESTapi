package com.example.demo.controller.criterios;

import com.example.demo.entity.criterios.CriterioTutorEmpresarial;
import com.example.demo.service.CriterioTutorEmpresarialService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/criterios/tutor/empresarial")
public class CriterioTutorEmpresarialController {
    private final CriterioTutorEmpresarialService criterioTutorEmpresarialService;

    public CriterioTutorEmpresarialController(CriterioTutorEmpresarialService criterioTutorEmpresarialService) {
        this.criterioTutorEmpresarialService = criterioTutorEmpresarialService;
    }

    @GetMapping("/by/school/{schoolName}")
    public List<CriterioTutorEmpresarial> getCriterioTutorEmpresarialBySchool(@PathVariable String schoolName){
        return criterioTutorEmpresarialService.getCriterioTutorEmpresarialBySchool(schoolName);
    }

    @GetMapping
    public List<CriterioTutorEmpresarial> getAllCriterioTutorEmpresarial(){
        return criterioTutorEmpresarialService.getAllCriterioTutorEmpresarial();
    }

    @PostMapping
    public CriterioTutorEmpresarial createCriterioTutorEmpresarial(@RequestBody CriterioTutorEmpresarial criterioTutorEmpresarial){
        return criterioTutorEmpresarialService.createCriterioTutorEmpresarial(criterioTutorEmpresarial);
    }

    @PutMapping
    public CriterioTutorEmpresarial updateCriterioTutorEmpresarial(@RequestBody CriterioTutorEmpresarial criterioTutorEmpresarial){
        return criterioTutorEmpresarialService.updateCriterioTutorEmpresarial(criterioTutorEmpresarial);
    }
}

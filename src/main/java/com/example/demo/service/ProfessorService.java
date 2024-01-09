package com.example.demo.service;

import com.example.demo.entity.Professor;
import com.example.demo.entity.User;
import com.example.demo.interfaces.ProfessorResponseProjection;
import com.example.demo.repository.ProfessorRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.responses.ProfessorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository, UserRepository userRepository) {
        this.professorRepository = professorRepository;
        this.userRepository = userRepository;
    }

    public Professor createProfessor (Professor professor){
        return professorRepository.save(professor);
    }

    public Iterable<Professor> getAllProfessors(){
        return professorRepository.findAll();
    }

    public Professor getProfessorById(String id){
        return professorRepository .findById(id).orElse(null);
    }

    public Professor updateProfessor(String id, Professor professor){
        Professor existingProfessor = professorRepository.findById(id).orElse(null);
        if(existingProfessor != null){
            existingProfessor.setProfessorAddress(professor.getProfessorAddress());
            existingProfessor.setProfessorOffice(professor.getProfessorOffice());
            existingProfessor.setProfessorGraduationYear(professor.getProfessorGraduationYear());
            existingProfessor.setProfessorSchoolName(professor.getProfessorSchoolName());
            existingProfessor.setProfessorWorkExperience(professor.getProfessorWorkExperience());
            return professorRepository.save(existingProfessor);
        }
        return null;
    }

    public void deleteProfessorById (String id){
        professorRepository.deleteById(id);
    }

    public Iterable<ProfessorResponseProjection> getProfessorsData (){
        return professorRepository.getProfessorsData();
    }
}

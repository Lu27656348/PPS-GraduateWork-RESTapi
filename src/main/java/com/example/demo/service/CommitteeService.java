package com.example.demo.service;

import com.example.demo.entity.Committee;
import com.example.demo.repository.CommitteeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class CommitteeService {

    private final CommitteeRepository committeeRepository;

    public CommitteeService(CommitteeRepository committeeRepository) {
        this.committeeRepository = committeeRepository;
    }

    public Iterable<Committee> getAllCommittees(){
        return committeeRepository.findAll();
    }

    public Iterable<Committee> getAllCommitteesBySchool( String schoolName) {
        return committeeRepository.getAllCommitteesBySchool(schoolName);
    }

    public Committee createCommittee(Committee committee){
        return committeeRepository.save(committee);
    }

    public Committee updateCommittee(String id, Committee committee){
        Committee search = committeeRepository.findById(id).orElse(null);
        if( search != null ){
            search.setCommitteeId(committee.getCommitteeId());
            search.setCommitteeDate(committee.getCommitteeDate());
            return committeeRepository.save(search);
        }
        return null;
    }

    public void deleteCommittee(String id){
        committeeRepository.deleteById(id);
    }
}

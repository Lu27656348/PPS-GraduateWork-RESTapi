package com.example.demo.service;

import com.example.demo.entity.Committee;
import com.example.demo.repository.CommitteeRepository;
import org.springframework.stereotype.Service;

@Service
public class CommitteeService {

    private final CommitteeRepository committeeRepository;

    public CommitteeService(CommitteeRepository committeeRepository) {
        this.committeeRepository = committeeRepository;
    }

    public Iterable<Committee> getAllCommittees(){
        return committeeRepository.findAll();
    }
}

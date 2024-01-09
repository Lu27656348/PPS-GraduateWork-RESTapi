package com.example.demo.repository;

import com.example.demo.entity.Committee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitteeRepository extends CrudRepository<Committee,String> {
}

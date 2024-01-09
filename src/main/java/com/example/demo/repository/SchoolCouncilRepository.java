package com.example.demo.repository;

import com.example.demo.entity.SchoolCouncils;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolCouncilRepository extends CrudRepository<SchoolCouncils,String> {
}

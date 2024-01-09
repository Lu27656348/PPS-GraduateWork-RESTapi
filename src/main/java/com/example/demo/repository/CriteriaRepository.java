package com.example.demo.repository;

import com.example.demo.entity.Criteria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriteriaRepository extends CrudRepository<Criteria,Integer> {
}

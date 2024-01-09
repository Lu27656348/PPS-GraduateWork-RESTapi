package com.example.demo.repository;

import com.example.demo.entity.Enterprises;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EnterpriseRepository extends CrudRepository<Enterprises,Integer> {
}

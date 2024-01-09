package com.example.demo.repository;

import com.example.demo.entity.Schools;
import org.springframework.data.repository.CrudRepository;

public interface SchoolRepository extends CrudRepository<Schools,String> {
}

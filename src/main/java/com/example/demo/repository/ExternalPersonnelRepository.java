package com.example.demo.repository;

import com.example.demo.entity.ExternalPersonnel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalPersonnelRepository extends CrudRepository<ExternalPersonnel,String> {
}

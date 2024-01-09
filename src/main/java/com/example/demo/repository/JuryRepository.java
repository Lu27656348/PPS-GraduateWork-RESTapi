package com.example.demo.repository;

import com.example.demo.entity.Jury;
import com.example.demo.interfaces.JuryPrimaryKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuryRepository extends CrudRepository<Jury, JuryPrimaryKey> {
}

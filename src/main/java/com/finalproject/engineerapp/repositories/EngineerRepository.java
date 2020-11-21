package com.finalproject.engineerapp.repositories;

import com.finalproject.engineerapp.model.Engineer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EngineerRepository extends CrudRepository<Engineer, Long> {
    List<Engineer> findAll();
}

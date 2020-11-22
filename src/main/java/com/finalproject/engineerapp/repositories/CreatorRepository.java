package com.finalproject.engineerapp.repositories;

import com.finalproject.engineerapp.model.Creator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreatorRepository extends CrudRepository<Creator, Long> {
    List<Creator> findAll();

}

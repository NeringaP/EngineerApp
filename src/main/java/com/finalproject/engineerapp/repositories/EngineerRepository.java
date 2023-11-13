package com.finalproject.engineerapp.repositories;

import com.finalproject.engineerapp.model.Engineer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

//@RepositoryRestResource(path="employees")
@Repository
public interface EngineerRepository extends CrudRepository<Engineer, Long> {
    List<Engineer> findAll();
}

package com.finalproject.engineerapp.repository;

import com.finalproject.engineerapp.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    List<Project> findAll();

}

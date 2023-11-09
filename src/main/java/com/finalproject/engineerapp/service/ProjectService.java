package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<Project> findAll();

    void save(Project project);

    void delete(Project project);

    Optional<Project> findById(Long id);
}

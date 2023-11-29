package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<Project> findAll();

    Project save(Project project);

    void delete(Long id);

    Project findById(Long id);
}

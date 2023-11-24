package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Project;
import com.finalproject.engineerapp.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> findAll() {
        List<Project> projects = projectRepository.findAll();
        return projects;
    }

    @Override
    public void save(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void delete(Long id) {
        Project project = findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid " +
                "project Id:" + id));
        projectRepository.delete(project);
    }

    @Override
    public Optional<Project> findById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        return project;
    }

}
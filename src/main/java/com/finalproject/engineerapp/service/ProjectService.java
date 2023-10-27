package com.finalproject.engineerapp.service;


import com.finalproject.engineerapp.model.Project;
import com.finalproject.engineerapp.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects;
    }

    public void updateProject(Project project) {
        projectRepository.save(project);
    }

    public void addProject(Project project) {
        projectRepository.save(project);
    }

    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }

    public Optional<Project> findProjectById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        return project;
    }

}

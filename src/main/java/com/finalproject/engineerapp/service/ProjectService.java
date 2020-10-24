package com.finalproject.engineerapp.service;


import com.finalproject.engineerapp.model.Project;
import com.finalproject.engineerapp.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getProjects() {
        Iterable<Project> projects = projectRepository.findAll();
        List<Project> projectsList = new ArrayList<>();
        for (Project project : projects) {
            projectsList.add(project);
        }
        return projectsList;
    }

    public Project updateProject(Project project) {
        projectRepository.save(project);

        return project;
    }

}

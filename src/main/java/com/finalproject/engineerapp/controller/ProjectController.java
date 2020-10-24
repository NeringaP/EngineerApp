package com.finalproject.engineerapp.controller;

import com.finalproject.engineerapp.model.Project;
import com.finalproject.engineerapp.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/")
    public List<Project> projects(Model model) {
        List<Project> projects = projectService.getProjects();
        model.addAttribute("projects", projects);
        return projects;
    }
    @PutMapping("/update")
    public Project update(@RequestBody Project project) {
        Project updatedProject = projectService.updateProject(project);
        return updatedProject;
    }

}

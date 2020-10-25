package com.finalproject.engineerapp.controller;

import com.finalproject.engineerapp.model.Project;
import com.finalproject.engineerapp.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public void update(@RequestBody Project project) {
        projectService.updateProject(project);
    }

    @PostMapping("/add")
    public void add(@RequestBody Project project) {
         projectService.addProject(project);
    }

    @GetMapping("/delete/{id}")
    public String projects(@PathVariable("id") long id, Model model)  {
        Project project = projectService.findProjectById(id).orElseThrow(() -> new IllegalArgumentException("Invalid " +
                "user Id:" + id));
        projectService.deleteProject(project);
        List<Project> projects = projectService.getProjects();
        model.addAttribute("projects", projects);
        return "projects";
    }
}

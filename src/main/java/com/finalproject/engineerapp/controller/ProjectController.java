package com.finalproject.engineerapp.controller;

import com.finalproject.engineerapp.model.Creator;
import com.finalproject.engineerapp.model.Engineer;
import com.finalproject.engineerapp.model.Project;
import com.finalproject.engineerapp.service.CreatorService;
import com.finalproject.engineerapp.service.EngineerService;
import com.finalproject.engineerapp.service.HouseService;
import com.finalproject.engineerapp.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private ProjectService projectService;

    private EngineerService engineerService;

    private CreatorService creatorService;

    private HouseService houseService;

    @Autowired
    public ProjectController(ProjectService projectService, EngineerService engineerService,
                             CreatorService creatorService, HouseService houseService) {
        this.projectService = projectService;
        this.engineerService = engineerService;
        this.creatorService = creatorService;
        this.houseService = houseService;
    }

    @GetMapping("/list")
    public String listProjects(Model model) {
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "list-projects";
    }

    @GetMapping("/create")
    public String showCreateNewProjectForm (Project project, Model model) {
        List<Engineer> engineers = engineerService.findAll();
        model.addAttribute("engineers", engineers);
        List<Creator> creators = creatorService.findAll();
        model.addAttribute("creators", creators);
        return "project_add";
    }

    @PostMapping("/add")
    public String add(Project project, Model model) {
         projectService.save(project);
         List<Project> projects = projectService.findAll();
         model.addAttribute("projects", projects);
         return "list-projects";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Project project = projectService.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Invalid project Id:" + id));
        model.addAttribute("project", project);
        List<Engineer> engineers = engineerService.findAll();
        model.addAttribute("engineers", engineers);
        List<Creator> creators = creatorService.findAll();
        model.addAttribute("creators", creators);
        return "project_edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Project project, Model model) {
        projectService.save(project);
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "list-projects";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model)  {
        Project project = projectService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid " +
                "project Id:" + id));
        projectService.delete(project);
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "list-projects";
    }

}

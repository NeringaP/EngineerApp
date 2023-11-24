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
        return "projects/list-projects";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd (Model model) {
        Project project = new Project();
        model.addAttribute("project", project);
        List<Engineer> engineers = engineerService.findAll();
        model.addAttribute("engineers", engineers);
        List<Creator> creators = creatorService.findAll();
        model.addAttribute("creators", creators);
        return "projects/project-form";
    }

    @PostMapping("/save")
    public String Project(@ModelAttribute("project") Project project) {
         projectService.save(project);
         return "redirect:/projects/list";
    }

    @GetMapping("/showFormForEdit")
    public String showFormForEdit(@RequestParam("projectId") Long id, Model model) {
        Project project = projectService.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Invalid project Id:" + id));
        model.addAttribute("project", project);
        List<Engineer> engineers = engineerService.findAll();
        model.addAttribute("engineers", engineers);
        List<Creator> creators = creatorService.findAll();
        model.addAttribute("creators", creators);
        return "projects/project-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("projectId") Long id)  {
        projectService.delete(id);
        return "redirect:/projects/list";
    }

}

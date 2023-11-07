package com.finalproject.engineerapp.controller;

import com.finalproject.engineerapp.model.Creator;
import com.finalproject.engineerapp.model.Engineer;
import com.finalproject.engineerapp.model.Project;
import com.finalproject.engineerapp.repositories.CreatorRepository;
import com.finalproject.engineerapp.repositories.EngineerRepository;
import com.finalproject.engineerapp.repositories.HouseRepository;
import com.finalproject.engineerapp.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private ProjectRepository projectRepository;

    private EngineerRepository engineerRepository;

    private CreatorRepository creatorRepository;

    private HouseRepository houseRepository;

    @Autowired
    public ProjectController(ProjectRepository projectRepository, EngineerRepository engineerRepository,
                             CreatorRepository creatorRepository, HouseRepository houseRepository) {
        this.projectRepository = projectRepository;
        this.engineerRepository = engineerRepository;
        this.creatorRepository = creatorRepository;
        this.houseRepository = houseRepository;
    }

    @GetMapping
    public String projects(Model model) {
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "projects";
    }

    @GetMapping("/create")
    public String showCreateNewProjectForm (Project project, Model model) {
        List<Engineer> engineers = engineerRepository.findAll();
        model.addAttribute("engineers", engineers);
        List<Creator> creators = creatorRepository.findAll();
        model.addAttribute("creators", creators);
        return "project_add";
    }

    @PostMapping("/add")
    public String add(Project project, Model model) {
         projectRepository.save(project);
         List<Project> projects = projectRepository.findAll();
         model.addAttribute("projects", projects);
         return "projects";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Invalid project Id:" + id));
        model.addAttribute("project", project);
        List<Engineer> engineers = engineerRepository.findAll();
        model.addAttribute("engineers", engineers);
        List<Creator> creators = creatorRepository.findAll();
        model.addAttribute("creators", creators);
        return "project_edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Project project, Model model) {
        projectRepository.save(project);
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "projects";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model)  {
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid " +
                "project Id:" + id));
        projectRepository.delete(project);
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "projects";
    }

}

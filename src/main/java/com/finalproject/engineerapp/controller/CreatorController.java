package com.finalproject.engineerapp.controller;

import com.finalproject.engineerapp.model.Creator;
import com.finalproject.engineerapp.model.Project;
import com.finalproject.engineerapp.service.CreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/creators")
public class CreatorController {

    private CreatorService creatorService;

    @Autowired
    public CreatorController(CreatorService creatorService) {
        this.creatorService = creatorService;
    }

    @GetMapping
    public String creators(Model model) {
        List<Creator> creators = creatorService.findAll();
        model.addAttribute("creators", creators);
        return "creators";
    }

    @GetMapping("/create")
    public String showCreateNewCreatorForm (Creator creator) {
        return "creator_add";
    }

    @PostMapping("/add")
    public String add(Creator creator, Model model) {
        creatorService.save(creator);
        List<Creator> creators = creatorService.findAll();
        model.addAttribute("creators", creators);
        return "creators";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Creator creator = creatorService.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Invalid creator Id:" + id));
        model.addAttribute("creator", creator);
        return "creator_edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Creator creator, Model model) {
        creatorService.save(creator);
        List<Creator> creators = creatorService.findAll();
        model.addAttribute("creators", creators);
        return "creators";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model)  {
        Creator creator = creatorService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid " +
                "creator Id:" + id));
        Set<Project> projects = creator.getProjects();
        for (Project project : projects) {
            project.setCreator(null);
        }
        creatorService.delete(creator);
        List<Creator> creators = creatorService.findAll();

        model.addAttribute("creators", creators);
        return "creators";
    }
}

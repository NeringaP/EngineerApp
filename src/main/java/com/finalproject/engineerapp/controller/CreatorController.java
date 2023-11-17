package com.finalproject.engineerapp.controller;

import com.finalproject.engineerapp.model.Creator;
import com.finalproject.engineerapp.model.Engineer;
import com.finalproject.engineerapp.model.Project;
import com.finalproject.engineerapp.service.CreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/list")
    public String listCreators(Model model) {
        List<Creator> creators = creatorService.findAll();
        model.addAttribute("creators", creators);
        return "creators/list-creators";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Creator creator = new Creator();
        model.addAttribute("creator", creator);
        return "creators/creator-form";
    }

    @PostMapping("/save")
    public String saveCreator(@ModelAttribute("creator") Creator creator) {
        creatorService.save(creator);
        return "redirect:/creators/list";
    }

    @GetMapping("/showFormForEdit")
    public String showFormForEdit(@RequestParam("creatorId") Long id, Model model) {
        Creator creator = creatorService.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Invalid creator Id:" + id));
        model.addAttribute("creator", creator);
        return "creators/creator-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("creatorId") Long id)  {
        Creator creator = creatorService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid " +
                "creator Id:" + id));
        Set<Project> projects = creator.getProjects();
        for (Project project : projects) {
            project.setCreator(null);
        }
        creatorService.delete(creator);
        return "redirect:/creators/list";
    }
}

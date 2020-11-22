package com.finalproject.engineerapp.controller;

import com.finalproject.engineerapp.model.Creator;
import com.finalproject.engineerapp.model.Engineer;
import com.finalproject.engineerapp.model.Project;
import com.finalproject.engineerapp.service.CreatorService;
import com.finalproject.engineerapp.service.EngineerService;
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


    @Autowired
    private CreatorService creatorService;

    @GetMapping
    public String creators(Model model) {
        List<Creator> creators = creatorService.getCreators();
        model.addAttribute("creators", creators);
        return "creators";
    }

    @GetMapping("/create")
    public String showCreateNewCreatorForm (Creator creator) {
        return "creator_add";
    }

    @PostMapping("/add")
    public String add(Creator creator, Model model) {
        creatorService.addCreator(creator);
        List<Creator> creators = creatorService.getCreators();
        model.addAttribute("creators", creators);
        return "creators";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Creator creator = creatorService.findCreatorById(id).orElseThrow(() -> new IllegalArgumentException(
                "Invalid creator Id:" + id));
        model.addAttribute("creator", creator);
        return "creator_edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Creator creator, Model model) {
        creatorService.updateCreator(creator);
        List<Creator> creators = creatorService.getCreators();
        model.addAttribute("creators", creators);
        return "creators";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model)  {
        Creator creator = creatorService.findCreatorById(id).orElseThrow(() -> new IllegalArgumentException("Invalid " +
                "creator Id:" + id));
        Set<Project> projects = creator.getProjects();
        for (Project project : projects) {
            project.setCreator(null);
        }
        creatorService.deleteCreator(creator);
        List<Creator> creators = creatorService.getCreators();

        model.addAttribute("creators", creators);
        return "creators";
    }
}

package com.finalproject.engineerapp.controller;

import com.finalproject.engineerapp.exception.InvalidIdException;
import com.finalproject.engineerapp.model.House;
import com.finalproject.engineerapp.model.Project;
import com.finalproject.engineerapp.service.HouseService;
import com.finalproject.engineerapp.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/houses")
public class HouseController {

    private ProjectService projectService;
    private HouseService houseService;

    @Autowired
    public HouseController(ProjectService projectService, HouseService houseService) {
        this.projectService = projectService;
        this.houseService = houseService;
    }

    @GetMapping
    public String houses(Model model) {
        List<House> houses = houseService.findAll();
        model.addAttribute("houses", houses);
        return "houses";
    }

    @GetMapping("/create")
    public String showCreateNewHouseForm (House house, Model model) {
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "house_add";
    }

    @PostMapping("/add")
    public String add(House house, Model model) {
        houseService.save(house);
        List<House> houses = houseService.findAll();
        model.addAttribute("houses", houses);
        return "houses";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        House house = houseService.findById(id).orElseThrow(() -> new InvalidIdException("Invalid house Id: " + id));
        model.addAttribute("house", house);
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "house_edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, House house, Model model) {
        houseService.save(house);
        List<House> houses = houseService.findAll();
        model.addAttribute("houses", houses);
        return "houses";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model)  {
        House house = houseService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid " +
                "house Id:" + id));
        houseService.delete(house);
        List<House> houses = houseService.findAll();
        model.addAttribute("houses", houses);
        return "houses";
    }


}

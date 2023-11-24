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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/list")
    public String listHouses(Model model) {
        List<House> houses = houseService.findAll();
        model.addAttribute("houses", houses);
        return "houses/list-houses";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd (Model model) {
        House house = new House();
        model.addAttribute("house", house);
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "houses/house-form";
    }

    @PostMapping("/save")
    public String saveHouse(@ModelAttribute("house") House house) {
        houseService.save(house);
        return "redirect:/houses/list";
    }

    @GetMapping("/showFormForEdit")
    public String showFormForEdit(@RequestParam("houseId") Long id, Model model) {
        House house = houseService.findById(id).orElseThrow(() -> new InvalidIdException("Invalid house Id: " + id));
        model.addAttribute("house", house);
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "houses/house-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("houseId") Long id)  {
        houseService.delete(id);
        return "redirect:/houses/list";
    }


}

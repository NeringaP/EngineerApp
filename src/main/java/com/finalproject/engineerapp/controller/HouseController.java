package com.finalproject.engineerapp.controller;

import com.finalproject.engineerapp.model.House;
import com.finalproject.engineerapp.model.Project;
import com.finalproject.engineerapp.service.HouseService;
import com.finalproject.engineerapp.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.NoSuchElementException;

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
    public String showFormForEdit(@RequestParam("houseId") String id, Model model) {
        try {
            Long houseId = Long.parseLong(id); // Convert the string to Long
            House house = houseService.findById(houseId);
            model.addAttribute("house", house);
            List<Project> projects = projectService.findAll();
            model.addAttribute("projects", projects);
            return "houses/house-form";
        } catch (NumberFormatException e) {
            model.addAttribute("exception", "Invalid house ID format: " + id);
            return "exception-page";
        } catch (NoSuchElementException e) {
            model.addAttribute("exception", "Invalid house ID: " + id);
            return "exception-page";
        } catch (Exception e) {
            model.addAttribute("exception", e.getMessage());
            return "exception-page";
        }
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("houseId") Long id, Model model)  {
        try {
            houseService.delete(id);
            return "redirect:/houses/list";
        } catch (Exception e) {
            model.addAttribute("exception", e.getMessage());
            return "exception-page";
        }

    }


}

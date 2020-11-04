package com.finalproject.engineerapp.controller;

import com.finalproject.engineerapp.model.House;
import com.finalproject.engineerapp.model.Project;
import com.finalproject.engineerapp.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/houses")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @GetMapping("/")
    public String houses(Model model) {
        List<House> houses = houseService.getHouses();
        model.addAttribute("houses", houses);
        return "houses";
    }
}

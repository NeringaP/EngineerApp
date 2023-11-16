package com.finalproject.engineerapp.controller;


import com.finalproject.engineerapp.model.Person;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PersonController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/person")
    public String showForm(Person person, Model model) {
        model.addAttribute("person", person);
        return "person-form";
    }

    @PostMapping("/processForm")
    public String processForm(@Valid @ModelAttribute("person") Person person, BindingResult bindingResult) {
        System.out.println("First name: |" + person.getFirstName() + "|");
        if(bindingResult.hasErrors()) {
            return "person-form";
        } else {
            return "person-info";
        }

    }
}

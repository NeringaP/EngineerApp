package com.finalproject.engineerapp.controller;

import com.finalproject.engineerapp.model.Engineer;
import com.finalproject.engineerapp.model.Project;
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
@RequestMapping("/engineers")
public class EngineerController {

    @Autowired
    private EngineerService engineerService;

    @GetMapping
    public String engineers(Model model) {
        List<Engineer> engineers = engineerService.getEngineers();
        model.addAttribute("engineers", engineers);
        return "engineers";
    }

    @GetMapping("/create")
    public String showCreateNewEngineerForm (Engineer engineer) {
        return "engineer_add";
    }

    @PostMapping("/add")
    public String add(Engineer engineer, Model model) {
        engineerService.addEngineer(engineer);
        List<Engineer> engineers = engineerService.getEngineers();
        model.addAttribute("engineers", engineers);
        return "engineers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Engineer engineer = engineerService.findEngineerById(id).orElseThrow(() -> new IllegalArgumentException(
                "Invalid engineer Id:" + id));
        model.addAttribute("engineer", engineer);
        return "engineer_edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Engineer engineer, Model model) {
        engineerService.updateEngineer(engineer);
        List<Engineer> engineers = engineerService.getEngineers();
        model.addAttribute("engineers", engineers);
        return "engineers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model)  {
        Engineer engineer = engineerService.findEngineerById(id).orElseThrow(() -> new IllegalArgumentException("Invalid " +
                "engineer Id:" + id));
        Set<Project> projects = engineer.getProjects();
        for (Project project : projects) {
            project.setEngineer(null);
        }
        engineerService.deleteEngineer(engineer);
        List<Engineer> engineers = engineerService.getEngineers();

        model.addAttribute("engineers", engineers);
        return "engineers";
    }

}

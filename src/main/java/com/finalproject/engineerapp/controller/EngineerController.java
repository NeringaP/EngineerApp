package com.finalproject.engineerapp.controller;

import com.finalproject.engineerapp.model.Engineer;
import com.finalproject.engineerapp.model.Project;
import com.finalproject.engineerapp.service.EngineerPDFExporter;
import com.finalproject.engineerapp.service.EngineerService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/engineers")
public class EngineerController {

    private EngineerService engineerService;

    @Autowired
    public EngineerController(EngineerService engineerService) {
        this.engineerService = engineerService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping
    public String engineers(Model model) {
        List<Engineer> engineers = engineerService.findAll();
        model.addAttribute("engineers", engineers);
        return "engineers";
    }

    @GetMapping("/create")
    public String showCreateNewEngineerForm (Engineer engineer, Model model) {
        model.addAttribute("engineer", engineer);
        return "engineer_add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("engineer") Engineer engineer, Model model, BindingResult bindingResult) {
        System.out.println("First name: |" + engineer.getFirstName() + "|");
        if(bindingResult.hasErrors()) {
            return "engineer_add";
        } else {
            engineerService.save(engineer);
            List<Engineer> engineers = engineerService.findAll();
            model.addAttribute("engineers", engineers);
            return "engineers";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Engineer engineer = engineerService.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Invalid engineer Id:" + id));
        model.addAttribute("engineer", engineer);
        return "engineer_edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Engineer engineer, Model model) {
        engineerService.save(engineer);
        List<Engineer> engineers = engineerService.findAll();
        model.addAttribute("engineers", engineers);
        return "engineers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model)  {
        Engineer engineer = engineerService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid " +
                "engineer Id:" + id));
        Set<Project> projects = engineer.getProjects();
        for (Project project : projects) {
            project.setEngineer(null);
        }
        engineerService.delete(engineer);
        List<Engineer> engineers = engineerService.findAll();

        model.addAttribute("engineers", engineers);
        return "engineers";
    }

    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd--HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=engineers--" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Engineer> engineerList = engineerService.findAll();

        EngineerPDFExporter exporter = new EngineerPDFExporter(engineerList);
        exporter.exportPDF(response);
    }

}

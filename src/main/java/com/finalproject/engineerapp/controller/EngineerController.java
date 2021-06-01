package com.finalproject.engineerapp.controller;

import com.finalproject.engineerapp.model.Engineer;
import com.finalproject.engineerapp.model.Project;
import com.finalproject.engineerapp.service.EngineerPDFExporter;
import com.finalproject.engineerapp.service.EngineerService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=engineers_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Engineer> engineerList = engineerService.getEngineers();

        EngineerPDFExporter exporter = new EngineerPDFExporter(engineerList);
        exporter.export(response);
    }

}

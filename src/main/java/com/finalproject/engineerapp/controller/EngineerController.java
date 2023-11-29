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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    private EngineerService engineerService;

    @Autowired
    public EngineerController(EngineerService engineerService) {
        this.engineerService = engineerService;
    }

    @GetMapping("/list")
    public String listEngineers(Model model) {
        List<Engineer> engineers = engineerService.findAll();
        model.addAttribute("engineers", engineers);
        return "engineers/list-engineers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd (Model model) {
        Engineer engineer = new Engineer();
        model.addAttribute("engineer", engineer);
        return "engineers/engineer-form";
    }

    @PostMapping("/save")
    public String saveEngineer(@ModelAttribute("engineer") Engineer engineer) {
        engineerService.save(engineer);
        return "redirect:/engineers/list";
    }

    @GetMapping("/showFormForEdit")
    public String showFormForEdit(@RequestParam("engineerId") Long id, Model model) {
        Engineer engineer = engineerService.findById(id);
        model.addAttribute("engineer", engineer);
        return "engineers/engineer-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("engineerId") Long id)  {
        engineerService.delete(id);
        return "redirect:/engineers/list";
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

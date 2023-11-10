package com.finalproject.engineerapp.controller.rest;

import com.finalproject.engineerapp.model.Engineer;
import com.finalproject.engineerapp.service.EngineerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EngineerRestController {

        private EngineerService engineerService;

        @Autowired
        public EngineerRestController(EngineerService engineerService) {
            this.engineerService = engineerService;
        }

        @GetMapping("/engineers")
        public List<Engineer> findAll() {
            return engineerService.findAll();
        }

        @GetMapping("/engineers/{id}")
        public Engineer getEngineer(@PathVariable Long id) {
            Engineer engineer = engineerService.findById(id).orElseThrow(() -> new IllegalArgumentException(
                    "Invalid engineer Id:" + id));
            return engineer;
        }


        @PostMapping("/engineers")
        public Engineer addEngineer(@RequestBody Engineer engineer) {
            return engineerService.save(engineer);
        }

        @PutMapping(value = "/engineers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public Engineer updateEngineer(@RequestBody Engineer engineer) {
            return engineerService.save(engineer);
        }

        @DeleteMapping("/engineers/{id}")
        public Long deleteEngineer(@PathVariable("id") Long id)  {
            return engineerService.deleteById(id);
        }

    }

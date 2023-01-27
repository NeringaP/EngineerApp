package com.finalproject.engineerapp.controller;

import com.finalproject.engineerapp.model.Authorities;
import com.finalproject.engineerapp.model.Creator;
import com.finalproject.engineerapp.model.User;
import com.finalproject.engineerapp.repositories.AuthoritiesRepository;
import com.finalproject.engineerapp.repositories.UserRepository;
import com.finalproject.engineerapp.service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class RegisterController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @GetMapping("/")
    public String index() {
        return "home";
    }
}

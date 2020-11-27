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

    @GetMapping("/login?logout")
    public String logout() {
        return "home";
    }

    @PostMapping("/adduser")
    public User addNewUser(@RequestBody User user) {
        userService.addUser(user);

        return user;
    }

    @PostMapping("/register")
    public User doRegister(@RequestBody User user) {
        String encodedPassword  = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        Authorities boardAuthority = new Authorities();
        boardAuthority.setAuthority("USER");
        boardAuthority.setUser(user);
        user.setAuthorities(boardAuthority);
        userService.addUser(user);

        return user;
    }

    @GetMapping("/teises")
    public List<Authorities> getAuthorities() {
        return authoritiesRepository.findAll();
    }

    @PostMapping("/addteise")
    public List<Authorities> addAuthority(@RequestBody Authorities authorities) {
        authoritiesRepository.save(authorities);

        return authoritiesRepository.findAll();
    }
}

package com.finalproject.engineerapp.controller;

import com.finalproject.engineerapp.model.Authorities;
import com.finalproject.engineerapp.model.User;
import com.finalproject.engineerapp.repositories.AuthoritiesRepository;
import com.finalproject.engineerapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class RegisterController {

    PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private AuthoritiesRepository authoritiesRepository;

    @Autowired
    public RegisterController(PasswordEncoder passwordEncoder, UserRepository userRepository,
                              AuthoritiesRepository authoritiesRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
    }

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @PostMapping("/adduser")
    public User addNewUser(@RequestBody User user) {
        userRepository.save(user);

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
        userRepository.save(user);

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

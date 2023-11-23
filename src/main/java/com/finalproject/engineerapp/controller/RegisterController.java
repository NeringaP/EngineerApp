package com.finalproject.engineerapp.controller;

import com.finalproject.engineerapp.model.Authority;
import com.finalproject.engineerapp.service.AuthoritiesService;
import com.finalproject.engineerapp.service.UserService;
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

    private UserService userService;

    private AuthoritiesService authoritiesService;

    @Autowired
    public RegisterController(PasswordEncoder passwordEncoder, UserService userService,
                              AuthoritiesService authoritiesService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.authoritiesService = authoritiesService;
    }

//    @PostMapping("/adduser")
//    public User addNewUser(@RequestBody User user) {
//        userService.save(user);
//
//        return user;
//    }

//    @PostMapping("/register")
//    public User doRegister(@RequestBody User user) {
//        String encodedPassword  = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//
//        Set<Authorities> authoritiesSet = new HashSet<>();
//        Authorities boardAuthority = new Authorities();
//        boardAuthority.setAuthority("USER");
//        boardAuthority.setUser(user);
//        authoritiesSet.add(boardAuthority);
//        user.setAuthorities(authoritiesSet);
//        userService.save(user);
//
//        return user;
//    }

    @GetMapping("/teises")
    public List<Authority> getAuthorities() {
        return authoritiesService.findAll();
    }

    @PostMapping("/addteise")
    public List<Authority> addAuthority(@RequestBody Authority authority) {
        authoritiesService.save(authority);

        return authoritiesService.findAll();
    }
}

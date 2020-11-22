package com.finalproject.engineerapp.controller;

import com.finalproject.engineerapp.model.User;
import com.finalproject.engineerapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class WelcomeController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/welcome")
    public String welcome(Principal principal, Authentication authentication) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        return "welcome, " + user.getUsername() + " ,you are authorized." +
                " Your posts will be created with your id - " + user.getId() +
                " you have assigned user role - " + user.getAuthorities().getAuthority();
    }

    @GetMapping("/cart")
    public String cart() {
        return "Cart is visible for everybody.";
    }
}

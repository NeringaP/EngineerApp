package com.finalproject.engineerapp.controller;

import com.finalproject.engineerapp.model.User;
import com.finalproject.engineerapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class WelcomeController {

    private UserService userService;

    @Autowired
    public WelcomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/welcome")
    public String welcome(Principal principal, Authentication authentication) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        return "welcome, " + user.getUsername() + " ,you are authorized." +
                " Your posts will be created with your id - " + user.getUsername() +
                " you have assigned user role - " + user.getAuthorities();
    }

    @GetMapping("/cart")
    public String cart() {
        return "Cart is visible for everybody.";
    }
}

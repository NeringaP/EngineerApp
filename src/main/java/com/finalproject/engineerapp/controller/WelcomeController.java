package com.finalproject.engineerapp.controller;

import com.finalproject.engineerapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    private UserService userService;

    @Autowired
    public WelcomeController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/welcome")
//    public String welcome(Principal principal, Authentication authentication) {
//        String username = principal.getName();
//        User user = userService.findByUsername(username);
//        return "welcome, " + user.getUsername() + " ,you are authorized." +
//                " Your posts will be created with your id - " + user.getId() +
//                " you have assigned user role - " + user.getAuthorities().getAuthority();
//    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
}

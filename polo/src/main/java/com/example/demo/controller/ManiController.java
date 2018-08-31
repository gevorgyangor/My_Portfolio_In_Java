package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ManiController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/")
    public String mainPage() {
        return "index";
    }


    @GetMapping("/login")
    public String login(ModelMap map) {
        map.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/loginUser")
    public String loginUser() {
        return "redirect:/";
    }

    @GetMapping("/blogPage")
    public String blogePage() {
        return "blog-single";
    }
}

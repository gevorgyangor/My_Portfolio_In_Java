package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.UserType;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        return "logForm";
    }

    @GetMapping("/register")
    public String registerUser(ModelMap map) {
        map.addAttribute("user", new User());
        return "regForm";
    }

    @PostMapping("/registerUser")
    public String registerUser(@ModelAttribute("user") User user, ModelMap map) {
        User user1 = new User();
        user1 = userRepository.findUserByEmail(user.getEmail());
        if (user.getUsername().equals(user1.getUsername())) {
            String errorMessage = "Already Username";
            map.addAttribute("errorMessage", errorMessage);
            return "redirect:/register";
        }
        user.setRepeatPassword(passwordEncoder.encode(user.getRepeatPassword()));
        user.setType(UserType.USER);
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/loginUser")
    public String loginUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = ((CurrentUser) userDetails).getUser();
        if (user.getType() == UserType.USER) {
            return "redirect:/";
        }
        return "admin";
    }

    @GetMapping("/blogPage")
    public String blogePage() {
        return "blog-single";
    }
}

package com.example.demo.controller;

import com.example.demo.mail.EmailServiceImpl;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class ManiController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailServiceImpl emailService;

    @GetMapping("/")
    public String mainPage(ModelMap map) {
        map.addAttribute("user", new User());
        return "index";
    }

    @GetMapping("/indexVideoPage")
    public String indexVideoPage() {
        return "index-video";
    }

    @GetMapping("/slideshowPage")
    public String slideshowPage() {
        return "index-slideshow";
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
        User user1 = null;
        user1 = userRepository.findUserByUsername(user.getUsername());
        if (user1 != null) {
            map.addAttribute("message", "Already Username");
            return "redirect:/register";
        }
        user.setRepeatPassword(passwordEncoder.encode(user.getRepeatPassword()));
        user.setType(UserType.USER);
        user.setToken(UUID.randomUUID().toString());
        userRepository.save(user);
        String url = String.format("http://localhost:8080/verify?token=%s&email=%s", user.getToken(), user.getEmail());
        String text = String.format("Dear %s student, you are registered%s", user.getName(), url);
        emailService.sendSimpleMessage(user.getEmail(), "Welcome  ", text);
        return "redirect:/login";
    }

    @GetMapping("/verify")
    public String verify(@RequestParam("token") String token, @RequestParam("email") String email) {
        User userByEmail = userRepository.findUserByEmail(email);
        if (userByEmail != null) {
            if (userByEmail.getToken() != null && userByEmail.getToken().equals(token)) {
                userByEmail.setToken(null);
                userByEmail.setVerify(true);
                userRepository.save(userByEmail);

            }
        }
        return "redirect:/";
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

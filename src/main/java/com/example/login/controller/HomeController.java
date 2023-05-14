package com.example.login.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class HomeController {

    @GetMapping
    public String homeLogin(
            Authentication auth, Model model) {

        if (auth == null) {
            return "home";
        }

        model.addAttribute("member", auth);
        return "loginHome";
    }
}

package com.example.login.controller;

import com.example.login.entity.Member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

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

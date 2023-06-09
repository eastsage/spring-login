package com.example.login.controller;

import com.example.login.service.LoginService;
import com.example.login.domain.dto.LoginDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
//    private final UserDetailsServiceImpl userDetailsService;

    @GetMapping("/login")
    public String loginForm(LoginDto loginDto) {
        return "login/loginForm";
    }

//    @PostMapping("login")
//    public String login(@Valid LoginVO loginVO, BindingResult bindingResult, HttpServletRequest request) {
//        if (bindingResult.hasErrors()) {
//            return "login/loginForm";
//        }
//        Member loginMember = loginService.login(loginVO.getUserId(), loginVO.getPassword());
//        UserDetails userDetails = loginService.loadUserByUsername(loginMember.getUserId());
//        if (loginMember == null) {
//            bindingResult.reject("loginFail", "userId or password error");
//            return "login/loginForm";
//        }
//
//        //login ok
//        HttpSession session = request.getSession();
//        session.setAttribute("loginMember", loginMember);
//
//        return "redirect:/";
//    }

//    @GetMapping("login/token")
//    @ResponseBody
//    public String login() {
//        String jwt = loginService.loginJwt("", "");
//        return jwt;
//    }

    @GetMapping("/memberPage")
    public String memberPage(Authentication auth, Model model) {
        model.addAttribute("member", auth);
        return "login/memberPage";
    }

    @PostMapping("logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
        return "redirect:/";
    }
}

package com.example.login.controller;

import com.example.login.entity.Member;
import com.example.login.service.LoginService;
import com.example.login.vo.LoginVO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(LoginVO loginVO) {
        return "login/loginForm";
    }

    @PostMapping("login")
    public String login(@Valid LoginVO loginVO, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        Member loginMember = loginService.login(loginVO.getUserId(), loginVO.getPassword());
        if (loginMember == null) {
            bindingResult.reject("loginFail", "userId or password error");
            return "login/loginForm";
        }

        //login ok
        HttpSession session = request.getSession();
        session.setAttribute("loginMember", loginMember);

        return "redirect:/";
    }

    @GetMapping("login/token")
    @ResponseBody
    public String login() {
        String jwt = loginService.loginJwt("", "");
        return jwt;
    }

    @GetMapping("/memberPage")
    public String memberPage(@SessionAttribute( name = "loginMember", required = false)Member loginMember, Model model) {
        model.addAttribute("member", loginMember);
        return "login/memberPage";
    }

    @PostMapping("logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}

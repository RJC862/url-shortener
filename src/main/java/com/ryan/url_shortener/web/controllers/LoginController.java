package com.ryan.url_shortener.web.controllers;

import com.ryan.url_shortener.domain.services.DbUserDetailsService;
import com.ryan.url_shortener.domain.services.UserService;
import com.ryan.url_shortener.dtos.LoginForm;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    private final DbUserDetailsService dbUserDetailsService;

    public LoginController(DbUserDetailsService dbUserDetailsService){
        this.dbUserDetailsService = dbUserDetailsService;
    }
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginForm", new LoginForm("", ""));
        return "login";
    }
}

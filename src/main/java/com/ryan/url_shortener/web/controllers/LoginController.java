package com.ryan.url_shortener.web.controllers;

import com.ryan.url_shortener.dtos.LoginForm;
import com.ryan.url_shortener.dtos.RegisterNewUserForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginForm", new LoginForm("", ""));
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("loginForm") @Valid LoginForm form,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            Model model){

        return "redirect:/";
    }
}

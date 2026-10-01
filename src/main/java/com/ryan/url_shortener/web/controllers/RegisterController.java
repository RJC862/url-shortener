package com.ryan.url_shortener.web.controllers;

import com.ryan.url_shortener.domain.models.RegisterNewUserCmd;
import com.ryan.url_shortener.domain.models.UserDto;
import com.ryan.url_shortener.domain.services.UserService;
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
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("registerNewUserForm", new RegisterNewUserForm("", "", ""));
        return "register";
    }

    @PostMapping("/register")
    String registerNewUser(@ModelAttribute("registerNewUserForm") @Valid RegisterNewUserForm form,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           Model model){

        if (userService.existsByEmail(form.email())) {
            bindingResult.rejectValue("email", "error.email.exists", "An account with this email already exists.");
        }

        if(userService.existsByUsername(form.username())) {
            bindingResult.rejectValue("username", "error.username.exists", "An account with this username already exists.");
        }

        if (!userService.isUsernameValid(form.username())) {
            bindingResult.rejectValue("username", "error.invalid.username", "Username must be alphanumeric.");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            RegisterNewUserCmd cmd = new RegisterNewUserCmd(form.username(), form.email(), form.password());
            UserDto newUser = userService.createUser(cmd);
            redirectAttributes.addFlashAttribute("successMessage", "User Successfully Registered. Welcome, " +
                    newUser.name());
            return "redirect:/";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed To Register User.");
        }

        return "redirect:/";
    }
}

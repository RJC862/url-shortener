package com.ryan.url_shortener.web.controllers;

import com.ryan.url_shortener.domain.entities.User;
import com.ryan.url_shortener.domain.models.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("api/user/me")
    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof User user){
            return "Welcome, " + user.getName() + ".";
        }

        return "Not logged in";
    }
}

package com.ryan.url_shortener.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterNewUserForm(
        @NotBlank(message = "Please create your username")
        @Size(max = 25, message = "Username length cannot exceed 25 characters")
        String username,

        @NotBlank(message = "Please enter your email")
        @Email(message = "Please provide a valid email address")
        String email,

        @NotBlank(message = "Please create a password")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password
) {
}

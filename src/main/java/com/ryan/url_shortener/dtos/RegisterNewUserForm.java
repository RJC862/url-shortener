package com.ryan.url_shortener.dtos;

import jakarta.validation.constraints.NotBlank;

public record RegisterNewUserForm(
        @NotBlank(message = "Please create your username")
        String userName,

        @NotBlank(message = "Please enter your email")
        String email,

        @NotBlank(message = "Please create a password")
        String password
) {
}

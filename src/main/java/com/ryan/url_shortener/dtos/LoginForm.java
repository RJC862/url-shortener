package com.ryan.url_shortener.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginForm(
        @NotBlank(message = "Please enter your username or email")
        String usernameOrEmail,

        @NotBlank(message = "Please enter your password")
        String password
) {
}

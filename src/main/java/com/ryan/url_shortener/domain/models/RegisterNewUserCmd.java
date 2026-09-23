package com.ryan.url_shortener.domain.models;

public record RegisterNewUserCmd(String username, String email, String password) {
}

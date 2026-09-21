package com.ryan.url_shortener.domain.models;

public record RegisterNewUserCmd(String userName, String email, String password) {
}

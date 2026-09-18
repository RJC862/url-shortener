package com.ryan.url_shortener.domain.models;

public record CreateShortUrlCmd(String originalUrl, Integer expiryInDays) {
}

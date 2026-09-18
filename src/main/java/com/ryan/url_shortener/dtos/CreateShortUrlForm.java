package com.ryan.url_shortener.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateShortUrlForm(
        @NotBlank(message = "Original URL is required")
        String originalUrl,
        @Nullable @Min(1) @Max(365) Integer expiryInDays){
}

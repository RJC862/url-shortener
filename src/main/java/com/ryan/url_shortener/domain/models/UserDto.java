package com.ryan.url_shortener.domain.models;

import java.io.Serializable;

/**
 * DTO for {@link com.ryan.url_shortener.domain.entities.User}
 */
public record UserDto(Long publicId, String name) implements Serializable {
}
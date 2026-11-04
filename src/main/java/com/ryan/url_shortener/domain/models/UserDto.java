package com.ryan.url_shortener.domain.models;

import com.ryan.url_shortener.domain.entities.UserEntity;

import java.io.Serializable;

/**
 * DTO for {@link UserEntity}
 */
public record UserDto(Long publicId, String name) implements Serializable {
}
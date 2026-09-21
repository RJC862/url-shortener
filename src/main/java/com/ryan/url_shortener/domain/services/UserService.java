package com.ryan.url_shortener.domain.services;

import com.ryan.url_shortener.domain.entities.User;
import com.ryan.url_shortener.domain.models.RegisterNewUserCmd;
import com.ryan.url_shortener.domain.models.UserDto;
import com.ryan.url_shortener.domain.repository.UserRepository;

import java.time.Instant;
import java.util.random.RandomGenerator;

public class UserService {
    private final EntityMapper mapper;

    public UserService(EntityMapper mapper, UserRepository userRepository) {
        this.mapper = mapper;
    }

    public UserDto createUser(RegisterNewUserCmd cmd) {

        var user = new User();

        var userName = cmd.userName();
        var password = cmd.password();
        var email = cmd.email();

        user.setEmail(email);
        user.setPassword(password);
        user.setName(userName);
        user.setCreatedAt(Instant.now());

        return mapper.toUserDto(user);
    }

}

package com.ryan.url_shortener.domain.services;

import com.ryan.url_shortener.domain.models.RegisterNewUserCmd;
import com.ryan.url_shortener.domain.models.UserDto;

public class UserService {
    private final EntityMapper mapper;

    public UserService(EntityMapper mapper) {
        this.mapper = mapper;
    }

    public UserDto createUser(RegisterNewUserCmd cmd) {

    }
}

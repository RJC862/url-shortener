package com.ryan.url_shortener.domain.services;

import com.ryan.url_shortener.domain.entities.User;
import com.ryan.url_shortener.domain.models.RegisterNewUserCmd;
import com.ryan.url_shortener.domain.models.UserDto;
import com.ryan.url_shortener.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;
import java.util.random.RandomGenerator;

@Service
public class UserService {
    private final EntityMapper mapper;
    private final UserRepository userRepository;

    public UserService(EntityMapper mapper, UserRepository userRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    public UserDto createUser(RegisterNewUserCmd cmd) {

        var user = new User();

        var username = cmd.username();
        var password = cmd.password();
        var email = cmd.email();
        var publicId = generateUniqueUserId();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(username);
        user.setCreatedAt(Instant.now());
        user.setPublicId(publicId);
        userRepository.save(user);
        return mapper.toUserDto(user);
    }

    Long generateUniqueUserId() {
        Long userId;
        do {
            userId = generateUserId();
        } while (!userRepository.existsByPublicId(userId));

        return userId;
    }
    Long generateUserId() {
        Random random = new Random();
        long lb = (long) Math.pow(10, 10);
        long ub = (long) Math.pow(10, 11)-1;
        return random.nextLong(lb, ub);
    }

}

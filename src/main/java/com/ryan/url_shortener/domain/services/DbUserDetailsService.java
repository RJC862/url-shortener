package com.ryan.url_shortener.domain.services;
import com.ryan.url_shortener.domain.entities.UserEntity;
import com.ryan.url_shortener.domain.models.UserDto;
import com.ryan.url_shortener.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DbUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public DbUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String content){ // Named loadUserByUsername, but really loads by username or email
        boolean isEmail = content.contains("@");

        Optional<UserEntity> found;

        if (isEmail) {
            found = userRepository.findByEmail(content);
        } else {
            found = userRepository.findByName(content);
        }

        UserEntity u = found.orElseThrow(() -> new UsernameNotFoundException("Username/Email Not Found"));

        return User.withUsername(u.getName())
                .password(u.getPassword())
                .roles("USER")
                .build();

    }
}

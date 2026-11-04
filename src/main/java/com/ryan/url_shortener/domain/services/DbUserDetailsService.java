package com.ryan.url_shortener.domain.services;
import com.ryan.url_shortener.domain.entities.UserEntity;
import com.ryan.url_shortener.domain.models.UserDto;
import com.ryan.url_shortener.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DbUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public DbUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserEntity u = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return User.withUsername(u.getName())
                .password(u.getPassword())
                .roles("USER")
                .build();
    }
}

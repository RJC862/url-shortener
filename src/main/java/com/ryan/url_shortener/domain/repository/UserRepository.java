package com.ryan.url_shortener.domain.repository;

import com.ryan.url_shortener.domain.entities.User;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

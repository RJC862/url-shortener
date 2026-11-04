package com.ryan.url_shortener.domain.repository;

import com.ryan.url_shortener.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByPublicId(Long publicId);
    boolean existsByName(String name);
    boolean existsByEmail(String email);

    Optional<UserEntity> findByName(String name);
}

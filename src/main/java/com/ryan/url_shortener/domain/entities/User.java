package com.ryan.url_shortener.domain.entities;

import com.ryan.url_shortener.domain.entities.models.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime createdAt;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
}




package com.flatironstudios.plately.user;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String name;

    protected User() {}

    public User(Role role, String email, String passwordHash, String name) {
        this.role = role;
        this.email = email;
        this.passwordHash = passwordHash;
        this.name = name;
    }

    public UUID getId() { return id; }
    public Role getRole() { return role; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getName() { return name; }
}
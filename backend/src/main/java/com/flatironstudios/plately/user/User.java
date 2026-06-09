package com.flatironstudios.plately.user;

import java.time.LocalDateTime;
import java.util.UUID;

import com.flatironstudios.plately.household.Household;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "household_id")
    private Household household;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected User() {}

    public User(String email, String passwordHash, String name) {
        this.role = Role.USER;
        this.email = email;
        this.passwordHash = passwordHash;
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() { return id; }
    public Role getRole() { return role; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getName() { return name; }
    public Household getHousehold() { return household; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setRole(Role role) { this.role = role; }
    public void setEmail(String email) { this.email = email; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setName(String name) { this.name = name; }
    public void setHouseHold(Household household) { this.household = household; }
}
package com.flatironstudios.plately.household;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.flatironstudios.plately.member.Member;

import jakarta.persistence.*;

@Entity
@Table(name = "household")
public class Household {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(precision = 10, scale = 2)
    private BigDecimal weeklyBudget;

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected Household() {}

    public Household(String name, BigDecimal weeklyBudget) {
        this.name = name;
        this.weeklyBudget = weeklyBudget;
        this.createdAt = LocalDateTime.now();
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getWeeklyBudget() { return weeklyBudget; }
    public List<Member> getMembers() { return members; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setName(String name) { this.name = name; }
    public void setWeeklyBudget(BigDecimal budget) { weeklyBudget = budget; }
}
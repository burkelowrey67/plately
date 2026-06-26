package com.flatironstudios.plately.member;

import java.util.Set;
import java.util.UUID;

import com.flatironstudios.plately.household.Household;

import jakarta.persistence.*;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "household_id", nullable = false)
    private Household household;

    @Column(nullable = false)
    private String name;

    @Column
    private DietType dietType;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "member_allergies", joinColumns = @JoinColumn(name = "member_id"))
    @Column(name = "allergy")
    private Set<Allergen> allergies;

    @Column(nullable = false)
    private int birthYear;

    @Column(nullable = false)
    private double heightMeters;
    
    @Column(nullable = false)
    private double weightKgs;
    
    @Column(nullable = false)
    private double weightGoalKgs;

    protected Member() {}

    public Member(
        Household household, String name, DietType dietType, Set<Allergen> allergies,
        int birthYear, double heightMeters, double weightKgs, double weightGoalKgs
    ) {
        this.household = household;
        this.name = name;
        this.dietType = dietType;
        this.allergies = allergies;
        this.birthYear = birthYear;
        this.heightMeters = heightMeters;
        this.weightKgs = weightKgs;
        this.weightGoalKgs = weightGoalKgs;
    }
    
    public UUID getId() { return id; }
    public String getName() { return name; }
    public Household getHousehold() { return household; }
    public DietType getDietType() { return dietType; }
    public Set<Allergen> getAllergies() { return allergies; }
    public int getBirthYear() { return birthYear; }
    public double getHeightMeters() { return heightMeters; }
    public double getWeightKgs() { return weightKgs; }
    public double getWeightGoalKgs() { return weightGoalKgs; }


    public void setName(String name) { this.name = name; }
    public void setDietType(DietType dietType) { this.dietType = dietType; }
    public void setAllergies(Set<Allergen> allergies) { this.allergies = allergies; }
    public void setBirthYear(int birthYear) { this.birthYear = birthYear; }
    public void setHeightMeters(double heightMeters) { this.heightMeters = heightMeters; }
    public void setWeightKgs(double weightKgs) { this.weightKgs = weightKgs; }
    public void setWeightGoalKgs(double weightGoalKgs) { this.weightGoalKgs = weightGoalKgs; }
}
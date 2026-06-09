package com.flatironstudios.plately.member;

import java.util.List;

import jakarta.validation.constraints.*;

public class MemberRequestDTO {

    @NotBlank
    private String name; 
    
    private DietType dietType; 
    
    private List<Allergen> allergies;

    @Min(1)
    @Max(120)
    private int ageYrs;

    @Min(0)
    @Max(3)
    private double heightMeters;

    @Min(1)
    @Max(500)
    private double weightKgs;

    @Min(1)
    @Max(500)
    private double weightGoalKgs;

    public MemberRequestDTO() {}


    public String getName() { return name; }
    public DietType getDietType() { return dietType; }
    public List<Allergen> getAllergies() { return allergies; }
    public int getAgeYrs() { return ageYrs; }
    public double getHeightMeters() { return heightMeters; }
    public double getWeightKgs() { return weightKgs; }
    public double getWeightGoalKgs() { return weightGoalKgs; }

}

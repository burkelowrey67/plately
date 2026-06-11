package com.flatironstudios.plately.member;

import java.time.Year;
import java.util.Set;
import java.util.UUID;

public record MemberResponseDTO(
    UUID id, String name, DietType dietType, 
    Set<Allergen> allergies, int ageYrs, 
    double heightMeters, double weightKgs, double weightGoalKgs) {
    
    public MemberResponseDTO(Member member) {
        this(
            member.getId(), member.getName(), member.getDietType(), 
            member.getAllergies(), Year.now().getValue() - member.getBirthYear(),
            member.getHeightMeters(), member.getWeightKgs(), member.getWeightGoalKgs()
        );
    }
}

package com.flatironstudios.plately.household;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.flatironstudios.plately.member.MemberResponseDTO;

public record HouseholdResponseDTO(
    UUID id, String name, BigDecimal weeklyBudget, 
    int memberCount, List<MemberResponseDTO> members, String ownerName
) {}   

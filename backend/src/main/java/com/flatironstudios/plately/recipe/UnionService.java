package com.flatironstudios.plately.recipe;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.flatironstudios.plately.member.Allergen;
import com.flatironstudios.plately.member.DietType;
import com.flatironstudios.plately.member.Member;

@Service
class UnionService {

    public Set<Allergen> allergenUnion(List<Member> members) {
        return members.stream().flatMap(member -> member.getAllergies().stream()).collect(Collectors.toSet());
    }

    public Set<DietType> dietTypeUnion(List<Member> members) {
        return members.stream().map(member -> member.getDietType()).collect(Collectors.toSet());
    }
}

package com.flatironstudios.plately.household;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flatironstudios.plately.exception.ResponseStatusException;
import com.flatironstudios.plately.member.Member;
import com.flatironstudios.plately.member.MemberRepository;
import com.flatironstudios.plately.member.MemberRequestDTO;
import com.flatironstudios.plately.user.UserService;

@Service
public class HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserService userService;

    public Household getHousehold(UUID householdId, UUID userId) {
        Household household = getHouseholdAndAuthorizeUser(householdId, userId);
        return household;
    }

    public String getName(UUID householdId, UUID userId) {
        Household household = getHouseholdAndAuthorizeUser(householdId, userId);
        String name = household.getName();
        
        if (name == null) throw new NoSuchElementException("Name not found");

        return name;
    }

    public void updateName(String name, UUID householdId, UUID userId) {
        Household household = getHouseholdAndAuthorizeUser(householdId, userId);
        household.setName(name);
    }

    public BigDecimal getBudget(UUID householdId, UUID userId) {
        Household household = getHouseholdAndAuthorizeUser(householdId, userId);
        BigDecimal budget = household.getWeeklyBudget();
        
        if (budget == null) throw new NoSuchElementException("Budget not found");

        return budget;
    } 

    public void updateBudget(BigDecimal budget, UUID householdId, UUID userId) {
        Household household = getHouseholdAndAuthorizeUser(householdId, userId);
        household.setWeeklyBudget(budget);
    }

    public UUID addMember(UUID householdId, MemberRequestDTO memberDTO, UUID userId) {
        Household household = getHouseholdAndAuthorizeUser(householdId, userId);

        Member member = new Member(
                            household, memberDTO.getName(), memberDTO.getDietType(), memberDTO.getAllergies(), 
                            (int) (Year.now().getValue() - memberDTO.getAgeYrs()), memberDTO.getHeightMeters(), 
                            memberDTO.getWeightKgs(), memberDTO.getWeightGoalKgs()
                        );  

        memberRepository.save(member);

        return member.getId();
    }   

    public List<Member> getMembers(UUID householdId, UUID userId) {
        Household household = getHouseholdAndAuthorizeUser(householdId, userId);
        List<Member> members = household.getMembers();

        if (members == null || members.size() == 0) {
            throw new NoSuchElementException("Members not found");
        }

        return members;
    }

    public Member getMember(UUID householdId, UUID memberId, UUID userId) {
        getHouseholdAndAuthorizeUser(householdId, userId);
        Member member = memberRepository.find(householdId, memberId);
        
        if (member == null) {
            throw new NoSuchElementException("Member not found");
        }

        return member;
    }

    public Member updateMember(UUID householdId, UUID memberId, MemberRequestDTO memberDTO, UUID userId) {
        Household household = getHouseholdAndAuthorizeUser(householdId, userId);
        Member member = new Member(
                    household, memberDTO.getName(), memberDTO.getDietType(), memberDTO.getAllergies(), 
                    (int) (Year.now().getValue() - memberDTO.getAgeYrs()), memberDTO.getHeightMeters(), 
                    memberDTO.getWeightKgs(), memberDTO.getWeightGoalKgs()
                );  
        
        memberRepository.save(member);

        return member;
    }

    private Household getHouseholdAndAuthorizeUser(UUID householdId, UUID userId) {
        Household household = householdRepository.findById(householdId)
            .orElseThrow(() -> new NoSuchElementException("Household not found"));

        if (!userService.findById(userId).getHousehold().getId().equals(household.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not belong to this household");
        }

        return household;
    }
}

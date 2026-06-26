package com.flatironstudios.plately.household;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Household createDefault() {
        return new Household("My household", new BigDecimal(9999));
    }

    public void save(Household household) {
        householdRepository.save(household);
    }

    public Household getHousehold(UUID userId) {
        Household household = userService.findById(userId).getHousehold();
        return household;
    }

    public String getName(UUID userId) {
        Household household = userService.findById(userId).getHousehold();
        String name = household.getName();
        
        if (name == null) throw new NoSuchElementException("Name not found");

        return name;
    }

    public void updateName(String name, UUID userId) {
        Household household = userService.findById(userId).getHousehold();
        household.setName(name);
    }

    public BigDecimal getBudget(UUID userId) {
        Household household = userService.findById(userId).getHousehold();
        BigDecimal budget = household.getWeeklyBudget();
        
        if (budget == null) throw new NoSuchElementException("Budget not found");

        return budget;
    } 

    public void updateBudget(BigDecimal budget, UUID userId) {
        Household household = userService.findById(userId).getHousehold();
        household.setWeeklyBudget(budget);
    }

    public Member addMember(MemberRequestDTO memberDTO, UUID userId) {
        Household household = userService.findById(userId).getHousehold();

        Member member = new Member(
                            household, memberDTO.getName(), memberDTO.getDietType(), memberDTO.getAllergies(), 
                            (int) (Year.now().getValue() - memberDTO.getAgeYrs()), memberDTO.getHeightMeters(), 
                            memberDTO.getWeightKgs(), memberDTO.getWeightGoalKgs()
                        );  

        memberRepository.save(member);

        return member;
    }   

    public List<Member> getMembers(UUID userId) {
        Household household = userService.findById(userId).getHousehold();
        List<Member> members = household.getMembers();

        if (members == null || members.size() == 0) {
            throw new NoSuchElementException("Members not found");
        }

        return members;
    }

    public Member getMember(UUID memberId, UUID userId) {
        List<Member> members = memberRepository.findByHouseholdId(userService.findById(userId).getHousehold().getId());

        Member member = members
            .stream()
            .filter(m -> m.getId().equals(memberId))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("This member does not belong to this household"));

        return member;
    }

    public Member updateMember(UUID memberId, MemberRequestDTO memberDTO, UUID userId) {

        Member member = getMember(memberId, userId);

        member.setName(memberDTO.getName());
        member.setDietType(memberDTO.getDietType());
        member.setBirthYear(Year.now().getValue() - memberDTO.getAgeYrs());
        member.setHeightMeters(memberDTO.getHeightMeters());
        member.setWeightKgs(memberDTO.getWeightKgs());
        member.setWeightGoalKgs(memberDTO.getWeightGoalKgs());
            
        memberRepository.save(member);

        return member;
    }
}

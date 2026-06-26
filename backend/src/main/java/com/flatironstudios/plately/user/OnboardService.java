package com.flatironstudios.plately.user;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.flatironstudios.plately.household.HouseholdService;
import com.flatironstudios.plately.member.MemberRequestDTO;

@Service
public class OnboardService {

    @Autowired
    UserService userService;

    @Autowired
    HouseholdService householdService;

    public void completeWelcome(UUID userId) {
        User user = userService.findById(userId);
        validateRequest(user, OnboardStep.WELCOME);
        user.setOnboardStep(OnboardStep.NAME);
        userService.save(user);
    }

    public void updateName(UUID userId, String name) {
        User user = userService.findById(userId);

        validateRequest(user, OnboardStep.NAME);

        user.setName(name);
        user.setOnboardStep(OnboardStep.HOUSEHOLD);
        userService.save(user);
    }

    public void updateHouseInfo(UUID userId, String name, BigDecimal budget) {
        User user = userService.findById(userId);

        validateRequest(user, OnboardStep.HOUSEHOLD);

        householdService.updateName(name, userId);
        householdService.updateBudget(budget, userId);
        user.setOnboardStep(OnboardStep.MEMBERS);
        userService.save(user);
    }

    public void addMember(UUID userId, MemberRequestDTO member) {
        User user = userService.findById(userId);

        validateRequest(user, OnboardStep.MEMBERS);
        householdService.addMember(member, userId);

        user.setOnboardStep(OnboardStep.COMPLETED);
        userService.save(user);
    }

    public void finish(UUID userId) {
        User user = userService.findById(userId);

        validateRequest(user, OnboardStep.MEMBERS);
        user.setOnboardStep(OnboardStep.COMPLETED);
        userService.save(user);
    }

    private void validateRequest(User user, OnboardStep currentStep) {
        if (user.getOnboardStep() == OnboardStep.COMPLETED) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User has already completed onboarding");
        }

        if (user.getOnboardStep().getValue() < currentStep.getValue()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User hasn't completed previous steps");
        }
    }
}

package com.flatironstudios.plately.user;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flatironstudios.plately.member.MemberRequestDTO;

@Controller
@RequestMapping("/api/onboard")
public class OnboardController {

    @Autowired
    private OnboardService onboardService;

    @PostMapping("/welcome")
    public ResponseEntity<Void> welcome(@AuthenticationPrincipal UUID userId) {
        onboardService.completeWelcome(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/name")
    public ResponseEntity<Void> name(
        @AuthenticationPrincipal UUID userId,
        @RequestBody UpdateNameRequestDTO request
    ) {
        onboardService.updateName(userId, request.getName());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/household")
    public ResponseEntity<Void> household(
        @AuthenticationPrincipal UUID userId,
        @RequestBody UpdateHouseholdRequestDTO request
    ) {
        onboardService.updateHouseInfo(userId, request.getName(), new BigDecimal(request.getBudget()));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/household/members")
    public ResponseEntity<Void> members(
        @AuthenticationPrincipal UUID userId,
        @RequestBody MemberRequestDTO request
    ) {
        onboardService.addMember(userId, request);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/finish")
    public ResponseEntity<Void> finish(
        @AuthenticationPrincipal UUID userId
    ) {
        onboardService.finish(userId);
        return ResponseEntity.noContent().build();
    }

}

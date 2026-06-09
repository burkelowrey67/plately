package com.flatironstudios.plately.household;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flatironstudios.plately.member.MemberRequestDTO;
import com.flatironstudios.plately.member.MemberResponseDTO;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/households")
public class HouseholdController {

    HouseholdService householdService;

    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @GetMapping("/{householdId}")
    public ResponseEntity<HouseholdResponseDTO> getHousehold(@PathVariable UUID householdId, @AuthenticationPrincipal UUID userId) {
        return ResponseEntity.ok(householdService.getHousehold(householdId, userId));
    }

    @GetMapping("/{householdId}/name")
    public ResponseEntity<Map<String, String>> getName(@PathVariable UUID householdId, @AuthenticationPrincipal UUID userId) {
        return ResponseEntity.ok(Map.of("name", householdService.getName(householdId, userId)));
    }

    @GetMapping("/{householdId}/budget")
    public ResponseEntity<Map<String, BigDecimal>> getBudget(@PathVariable UUID householdId, @AuthenticationPrincipal UUID userId) {
        return ResponseEntity.ok(Map.of("budget", householdService.getBudget(householdId, userId)));
    }

    @PatchMapping("/{householdId}/name")
    public ResponseEntity<Map<String, String>> updateName(
        @PathVariable UUID householdId, 
        @AuthenticationPrincipal UUID userId,
        @RequestBody @Valid UpdateNameRequestDTO request
    ) {
        householdService.updateName(request.getName(), householdId, userId);
        return ResponseEntity.ok(Map.of("name", request.getName()));
    }

    @PatchMapping("/{householdId}/budget")
    public ResponseEntity<Map<String, BigDecimal>> updateBudget(
        @PathVariable UUID householdId, 
        @AuthenticationPrincipal UUID userId,
        @RequestBody @Valid UpdateBudgetRequestDTO request
    ) {
        householdService.updateBudget(request.getWeeklyBudget(), householdId, userId);
        return ResponseEntity.ok(Map.of("weeklyBudget", request.getWeeklyBudget()));
    }


    @PostMapping("/{householdId}/members")
    public ResponseEntity<Map<String, UUID>> addMember(
            @PathVariable UUID householdId,
            @RequestBody @Valid MemberRequestDTO request,
            @AuthenticationPrincipal UUID userId, 
            HttpServletResponse response
        ) {
        
        UUID memberId = householdService.addMember(householdId, request, userId);
        return ResponseEntity.ok(Map.of("id", memberId));
    }

    @GetMapping("/{householdId}/members")
    public ResponseEntity<Map<String, List<MemberResponseDTO>>> getMembers(@PathVariable UUID householdId, @AuthenticationPrincipal UUID userId) {
        List<MemberResponseDTO> members = householdService.getMembers(householdId, userId);
        return ResponseEntity.ok(Map.of("members", members));
    }

    @GetMapping("/{householdId}/members/{memberId}")
    public ResponseEntity<MemberResponseDTO> getMember(@PathVariable UUID householdId, @PathVariable UUID memberId, @AuthenticationPrincipal UUID userId) {
        MemberResponseDTO member = householdService.getMember(householdId, memberId, userId);
        return ResponseEntity.ok(member);
    }

    @PutMapping("{householdId}/members/{memberId}")
    public ResponseEntity<MemberResponseDTO> updateMember(
            @PathVariable UUID householdId,
            @PathVariable UUID memberId,
            @Valid @RequestBody MemberRequestDTO request,
            @AuthenticationPrincipal UUID userId) {

        MemberResponseDTO updated = householdService.updateMember(householdId, memberId, request, userId);
        return ResponseEntity.ok(updated);
    }
}

package com.flatironstudios.plately.household;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.flatironstudios.plately.member.Member;
import com.flatironstudios.plately.member.MemberRequestDTO;
import com.flatironstudios.plately.member.MemberResponseDTO;
import com.flatironstudios.plately.user.User;
import com.flatironstudios.plately.user.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/household")
public class HouseholdController {

    @Autowired
    private HouseholdService householdService;

    @Autowired 
    private UserService userService;

    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @GetMapping("/info")
    public ResponseEntity<HouseholdResponseDTO> getHousehold(@AuthenticationPrincipal UUID userId) {
        Household household = householdService.getHousehold(userId);
        User user = userService.findById(userId);

        return ResponseEntity.ok(new HouseholdResponseDTO(
            household.getId(), household.getName(), household.getWeeklyBudget(), household.getMembers().size(), 
            household.getMembers().stream().map(MemberResponseDTO::new).toList(),
            user.getName()
        ));
    }

    @GetMapping("/name")
    public ResponseEntity<Map<String, String>> getName(@AuthenticationPrincipal UUID userId) {
        return ResponseEntity.ok(Map.of("name", householdService.getName(userId)));
    }

    @GetMapping("/budget")
    public ResponseEntity<Map<String, BigDecimal>> getBudget(@AuthenticationPrincipal UUID userId) {
        return ResponseEntity.ok(Map.of("budget", householdService.getBudget(userId)));
    }

    @PatchMapping("/name")
    public ResponseEntity<Map<String, String>> updateName(
        
        @AuthenticationPrincipal UUID userId,
        @RequestBody @Valid UpdateNameRequestDTO request
    ) {
        householdService.updateName(request.getName(), userId);
        return ResponseEntity.ok(Map.of("name", request.getName()));
    }

    @PatchMapping("/budget")
    public ResponseEntity<Map<String, BigDecimal>> updateBudget(
        
        @AuthenticationPrincipal UUID userId,
        @RequestBody @Valid UpdateBudgetRequestDTO request
    ) {
        householdService.updateBudget(request.getWeeklyBudget(), userId);
        return ResponseEntity.ok(Map.of("weeklyBudget", request.getWeeklyBudget()));
    }


    @PostMapping("/members")
    public ResponseEntity<MemberResponseDTO> addMember(
            @RequestBody @Valid MemberRequestDTO request,
            @AuthenticationPrincipal UUID userId, 
            HttpServletResponse response
        ) {
        
        Member member = householdService.addMember(request, userId);
        return ResponseEntity.ok(new MemberResponseDTO(member));
    }

    @GetMapping("/members")
    public ResponseEntity<Map<String, List<MemberResponseDTO>>> getMembers(@AuthenticationPrincipal UUID userId) {
        List<Member> members = householdService.getMembers(userId);
        List<MemberResponseDTO> memberResponses = members.stream().map(MemberResponseDTO::new).toList();
        return ResponseEntity.ok(Map.of("members", memberResponses));
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<MemberResponseDTO> getMember(@PathVariable UUID memberId, @AuthenticationPrincipal UUID userId) {
        Member member = householdService.getMember(memberId, userId);
        return ResponseEntity.ok(new MemberResponseDTO(member));
    }

    @PutMapping("/members/{memberId}")
    public ResponseEntity<MemberResponseDTO> updateMember(
            @PathVariable UUID memberId,
            @Valid @RequestBody MemberRequestDTO request,
            @AuthenticationPrincipal UUID userId) {

        Member updated = householdService.updateMember(memberId, request, userId);
        return ResponseEntity.ok(new MemberResponseDTO(updated));
    }
}

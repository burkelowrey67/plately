package com.flatironstudios.plately.user;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PatchMapping("/name") 
    public ResponseEntity<Void> updateName(
        @Valid @RequestBody UpdateNameRequestDTO request, 
        @AuthenticationPrincipal UUID userId
    ) {
        userService.updateName(userId, request.getName());
        return ResponseEntity.noContent().build();
    }
}

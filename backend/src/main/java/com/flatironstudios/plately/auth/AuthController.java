package com.flatironstudios.plately.auth;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.flatironstudios.plately.auth.dto.AuthResult;
import com.flatironstudios.plately.auth.dto.LoginRequest;
import com.flatironstudios.plately.auth.dto.RegisterRequest;
import com.flatironstudios.plately.security.CookieFactory;
import com.flatironstudios.plately.user.UserResponseDTO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody RegisterRequest request, HttpServletResponse response) {
        AuthResult result = authService.register(request);
        Cookie cookie = CookieFactory.createCookie(result.token());
        response.addCookie(cookie);

        return new ResponseEntity<>(result.user(), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        AuthResult result = authService.login(request);
        Cookie cookie = CookieFactory.createCookie(result.token());
        response.addCookie(cookie);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        Cookie cookie = CookieFactory.createExpiredCookie();
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> me(@AuthenticationPrincipal UUID id) {
        return ResponseEntity.ok(authService.me(id));
    }
}
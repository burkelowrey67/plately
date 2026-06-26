package com.flatironstudios.plately.auth;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.flatironstudios.plately.auth.dto.AuthResult;
import com.flatironstudios.plately.auth.dto.LoginRequest;
import com.flatironstudios.plately.auth.dto.RegisterRequest;
import com.flatironstudios.plately.security.CookieFactory;
import com.flatironstudios.plately.user.UserResponseDTO;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody RegisterRequest request, HttpServletResponse response) {
        AuthResult result = authService.register(request);
        ResponseCookie cookie = CookieFactory.createCookie(result.token());
        response.addHeader(
            HttpHeaders.SET_COOKIE,
            cookie.toString()
        );

        return new ResponseEntity<>(result.user(), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        AuthResult result = authService.login(request);
        ResponseCookie cookie = CookieFactory.createCookie(result.token());
        response.addHeader(
            HttpHeaders.SET_COOKIE,
            cookie.toString()
        );

        return ResponseEntity.ok(result.user());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        ResponseCookie cookie = CookieFactory.createExpiredCookie();
        response.addHeader(
            HttpHeaders.SET_COOKIE,
            cookie.toString()
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> me(@AuthenticationPrincipal UUID id) {
        return ResponseEntity.ok(authService.me(id));
    }
}
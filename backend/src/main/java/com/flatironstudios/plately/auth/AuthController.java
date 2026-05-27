package com.flatironstudios.plately.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flatironstudios.plately.auth.dto.AuthResult;
import com.flatironstudios.plately.auth.dto.LoginRequest;
import com.flatironstudios.plately.auth.dto.RegisterRequest;
import com.flatironstudios.plately.security.CookieFactory;
import com.flatironstudios.plately.user.dto.UserResponse;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request, HttpServletResponse response) {
        AuthResult result = authService.register(request);
        Cookie cookie = CookieFactory.createCookie(result.token());
        response.addCookie(cookie);

        return ResponseEntity.ok(result.user());
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        AuthResult result = authService.login(request);
        Cookie cookie = CookieFactory.createCookie(result.token());
        response.addCookie(cookie);

        return ResponseEntity.ok(result.user());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        Cookie cookie = CookieFactory.createExpiredCookie();
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }
}
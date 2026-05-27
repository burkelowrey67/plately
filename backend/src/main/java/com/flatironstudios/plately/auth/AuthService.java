package com.flatironstudios.plately.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flatironstudios.plately.auth.dto.AuthResult;
import com.flatironstudios.plately.auth.dto.LoginRequest;
import com.flatironstudios.plately.auth.dto.RegisterRequest;
import com.flatironstudios.plately.security.JwtService;
import com.flatironstudios.plately.user.*;
import com.flatironstudios.plately.user.dto.UserResponse;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;


    public AuthResult register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already registered");
        }
        
        String hashed = passwordEncoder.encode(request.password());
        User user = new User(Role.USER, request.email(), hashed, request.name());
        userRepository.save(user);

        return new AuthResult(
            jwtService.generateToken(user), new UserResponse(
                user.getId(), user.getName(), user.getEmail()
            )
        );
    }

    public AuthResult login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        return new AuthResult(
            jwtService.generateToken(user), new UserResponse(
                user.getId(), user.getName(), user.getEmail()
            )
        );
    }
}

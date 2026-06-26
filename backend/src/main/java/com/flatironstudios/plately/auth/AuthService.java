package com.flatironstudios.plately.auth;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flatironstudios.plately.auth.dto.AuthResult;
import com.flatironstudios.plately.auth.dto.LoginRequest;
import com.flatironstudios.plately.auth.dto.RegisterRequest;
import com.flatironstudios.plately.exception.ResponseStatusException;
import com.flatironstudios.plately.household.Household;
import com.flatironstudios.plately.household.HouseholdService;
import com.flatironstudios.plately.security.JwtService;
import com.flatironstudios.plately.user.*;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private HouseholdService householdService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Transactional
    public AuthResult register(RegisterRequest request) {
        if (userService.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already registered");
        }
        
        Household houshold = householdService.createDefault();
        householdService.save(houshold);

        
        String hashed = passwordEncoder.encode(request.getPassword());
        User user = userService.createNewUser(request.getEmail(), hashed);

        user.setHouseHold(houshold);

        return new AuthResult(
            jwtService.generateToken(user), new UserResponseDTO(
                user.getId(), user.getName(), user.getEmail(), user.getHousehold().getId(), user.getOnboardStep()
            )
        );
    }

    public AuthResult login(LoginRequest request) {
        User user = userService.findByEmail(request.email());

        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        return new AuthResult(
            jwtService.generateToken(user), new UserResponseDTO(
                user.getId(), user.getName(), user.getEmail(), user.getHousehold().getId(), user.getOnboardStep()
            )
        );
    }

    public UserResponseDTO me(UUID userId) {
        User user = userService.findById(userId);
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getHousehold().getId(), user.getOnboardStep());
    }
}

package com.flatironstudios.plately.auth;

import java.math.BigDecimal;
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
import com.flatironstudios.plately.household.HouseholdRepository;
import com.flatironstudios.plately.security.JwtService;
import com.flatironstudios.plately.user.*;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private HouseholdRepository householdRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Transactional
    public AuthResult register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already registered");
        }
        
        Household houshold = new Household("My household", new BigDecimal(9999));
        householdRepository.save(houshold);

        
        String hashed = passwordEncoder.encode(request.password());
        User user = new User(request.email(), hashed, request.name());
        userRepository.save(user);

        user.setHouseHold(houshold);

        return new AuthResult(
            jwtService.generateToken(user), new UserResponseDTO(
                user.getId(), user.getName(), user.getEmail(), user.getHousehold().getId()
            )
        );
    }

    public AuthResult login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,"User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        return new AuthResult(
            jwtService.generateToken(user), new UserResponseDTO(
                user.getId(), user.getName(), user.getEmail(), user.getHousehold().getId()
            )
        );
    }

    public UserResponseDTO me(UUID userId) {
        User user = userRepository.getReferenceById(userId);
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getHousehold().getId());
    }
}

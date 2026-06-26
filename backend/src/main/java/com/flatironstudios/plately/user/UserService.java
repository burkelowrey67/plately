package com.flatironstudios.plately.user;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createNewUser(String email, String passwordHash) {
        User user = new User(email, passwordHash);
        userRepository.save(user);
        return user;
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
            new NoSuchElementException("User was not found"));
    }

    public User findById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User was not found"));
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void updateName(UUID userId, String name) {
        User user = findById(userId); 
        user.setName(name);
        userRepository.save(user);
    }

    public void updateOnboardStep(UUID userId, OnboardStep onboardingStep) {

    }
}

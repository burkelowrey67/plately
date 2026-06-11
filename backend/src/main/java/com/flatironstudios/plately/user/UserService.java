package com.flatironstudios.plately.user;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User was not found"));
    }
}

package com.flatironstudios.plately.user;

import java.util.UUID;

public record UserResponseDTO(UUID id, String name, String email, UUID householdId, OnboardStep onboardStep) {}

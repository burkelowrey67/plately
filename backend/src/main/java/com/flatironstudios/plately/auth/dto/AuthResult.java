package com.flatironstudios.plately.auth.dto;

import com.flatironstudios.plately.user.UserResponseDTO;

public record AuthResult(String token, UserResponseDTO user) {}

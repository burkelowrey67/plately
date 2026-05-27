package com.flatironstudios.plately.auth.dto;

import com.flatironstudios.plately.user.dto.UserResponse;

public record AuthResult(String token, UserResponse user) {}

package com.flatironstudios.plately.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @NotBlank(message = "Email is required")
    @Email
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 12, message = "Password must be at least 8 characters and less than 12")
    String password;

    public String getEmail() { return email; }
    public String getPassword() { return password; }
}

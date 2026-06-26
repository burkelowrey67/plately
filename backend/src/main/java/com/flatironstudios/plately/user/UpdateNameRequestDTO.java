package com.flatironstudios.plately.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateNameRequestDTO {

    @NotBlank(message = "Name is required")
    @Size(max = 12, message = "Name must be less than 13 characters")
    @Pattern(
        regexp = "^[A-Za-z]+$",
        message = "Name must contain only letters"
    )
    private String name;

    public UpdateNameRequestDTO() {}

    public String getName() { return name; }
}

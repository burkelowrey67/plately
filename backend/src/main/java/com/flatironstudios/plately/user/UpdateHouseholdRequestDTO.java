package com.flatironstudios.plately.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

class UpdateHouseholdRequestDTO{

    @NotBlank(message = "Name is required")
    @Size(max = 24, message = "Name must be less than 25 characters")
    @Pattern(
        regexp = "^[A-Za-z]+$",
        message = "Name must contain only letters"
    )
    private String name;

    @NotBlank(message = "Budget is required")
    @Size(max = 9999, message = "Budget must be less than $10,000")
    private Double budget;

    public UpdateHouseholdRequestDTO() {}

    public String getName() { return name; }
    
    public Double getBudget() { return budget; }
}

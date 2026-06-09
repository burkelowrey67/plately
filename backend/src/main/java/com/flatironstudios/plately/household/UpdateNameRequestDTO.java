package com.flatironstudios.plately.household;

import jakarta.validation.constraints.NotNull;

public class UpdateNameRequestDTO {
    @NotNull
    private String name;

    public UpdateNameRequestDTO() {}

    public String getName() { return name; }
}

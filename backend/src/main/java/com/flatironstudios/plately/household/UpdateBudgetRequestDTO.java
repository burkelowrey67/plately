package com.flatironstudios.plately.household;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class UpdateBudgetRequestDTO {
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal weeklyBudget;

    public UpdateBudgetRequestDTO() {}

    public BigDecimal getWeeklyBudget() { return weeklyBudget; }
}

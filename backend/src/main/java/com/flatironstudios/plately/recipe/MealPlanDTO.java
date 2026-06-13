package com.flatironstudios.plately.recipe;

import java.util.List;

public class MealPlanDTO {
    private List<SpoonacularRecipeResponseDTO> plans;

    public void addPlan(SpoonacularRecipeResponseDTO plan) {
        plans.add(plan);
    }
}

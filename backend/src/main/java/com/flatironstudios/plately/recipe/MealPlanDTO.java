package com.flatironstudios.plately.recipe;

import java.util.List;

public class MealPlanDTO {
    private List<SpoonacularSearchResponseDTO> plans;

    public void addPlan(SpoonacularSearchResponseDTO plan) {
        plans.add(plan);
    }
}

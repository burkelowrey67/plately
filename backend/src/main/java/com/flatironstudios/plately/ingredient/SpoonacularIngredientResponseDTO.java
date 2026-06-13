package com.flatironstudios.plately.ingredient;

public record SpoonacularIngredientResponseDTO(
    int id,
    String name,
    String original,
    String image,
    NutritionDTO nutrition
) {}

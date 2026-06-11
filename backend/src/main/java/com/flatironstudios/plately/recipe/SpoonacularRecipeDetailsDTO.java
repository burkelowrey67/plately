package com.flatironstudios.plately.recipe;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flatironstudios.plately.ingredient.IngredientDTO;
import com.flatironstudios.plately.ingredient.NutritionDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SpoonacularRecipeDetailsDTO(
    int id, String title, String image, 
    Integer readyInMinutes, Integer servings,
    Boolean vegetarian, Boolean vegan, Boolean glutenFree, Boolean dairyFree,
    String summary, String instructions,
    List<String> cuisines, List<String> dishTypes, List<String> diets,
    NutritionDTO nutrition, List<IngredientDTO> extendedIngredients
) {
    @Override
    public boolean equals(Object o) {
        if ((Object) o instanceof SpoonacularRecipeDetailsDTO r) {
            return r.id() == id;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

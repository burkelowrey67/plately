package com.flatironstudios.plately.shoppinglist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flatironstudios.plately.ingredient.IngredientDTO;
import com.flatironstudios.plately.ingredient.IngredientService;
import com.flatironstudios.plately.recipe.RecipeService;
import com.flatironstudios.plately.recipe.SpoonacularRecipeDetailsDTO;

@Service
public class ShoppingListService {
    
    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private RecipeService recipeService;

    private static final Set<String> PANTRY_STAPLES = Set.of(
        "salt",
        "black pepper",
        "olive oil",
        "vegetable oil",
        "butter",
        "all-purpose flour",
        "sugar",
        "brown sugar",
        "baking powder",
        "baking soda",
        "vanilla extract",
        "salt and pepper"
    );

    public List<IngredientDTO> generateShoppingList(List<Integer> recipeIds) {
        List<IngredientDTO> ingredients = new ArrayList<>();

        for (int id : recipeIds) {
            SpoonacularRecipeDetailsDTO recipeDetails = recipeService.findRecipe(id);
            ingredients.addAll(recipeDetails.extendedIngredients()
                .stream()
                .filter(ingredient -> !PANTRY_STAPLES.contains(ingredient.name().toLowerCase()))
                .toList()
            );
        }

        Map<Integer, IngredientDTO> union = new HashMap<>();
        
        for (IngredientDTO ingredient : ingredients) {
            Integer id = ingredient.id();

            if (union.containsKey(id)) {
                IngredientDTO copy = union.get(id);

                String copyUnit = copy.unit();
                String ingredientUnit = ingredient.unit();

                double copyAmount = ingredientService.convert(
                    copy.name(), copy.amount(), 
                    ingredientService.parseUnit(copyUnit == null || copyUnit.isEmpty() ? "Count" : copyUnit),
                    ingredientService.parseUnit(ingredientUnit == null || ingredientUnit.isEmpty() ? "Count" : ingredientUnit)
                );

                union.put(id, new IngredientDTO(
                    id,
                    ingredient.name(), 
                    ingredient.amount() + copyAmount, 
                    ingredient.unit()
                ));
            }

            else union.put(id, ingredient);
        }

        return union.values().stream().toList();
    }
}

package com.flatironstudios.plately.shoppinglist;

import com.flatironstudios.plately.ingredient.IngredientDTO;
import com.flatironstudios.plately.ingredient.IngredientService;
import com.flatironstudios.plately.recipe.RecipeService;
import com.flatironstudios.plately.recipe.SpoonacularRecipeDetailsDTO;
import com.flatironstudios.plately.user.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@ExtendWith(MockitoExtension.class)
class ShoppingListServiceTest {

    @InjectMocks
    private ShoppingListService service;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RecipeService recipeService;

    @Mock IngredientService ingredientService;

    @Test
    void debugGenerateShoppingList() {

        RestTemplate restTemplate = new RestTemplate();

        List<Integer> recipeIds = List.of(643061, 638626);
        List<IngredientDTO> ingredients = new ArrayList<>();

        for (int id : recipeIds) {
            String url = UriComponentsBuilder
                .fromUriString("https://api.spoonacular.com" + "/recipes/" + id + "/information")
                .queryParam("apiKey", "719b8aa31e0e492a875050ae1606bfc0")
                .queryParam("includeNutrition", true)
                .toUriString();

            SpoonacularRecipeDetailsDTO result = restTemplate.getForObject(url, SpoonacularRecipeDetailsDTO.class);
            if (result == null) throw new NoSuchElementException("Recipe not found");

            ingredients.addAll(result.extendedIngredients());
        }

        Map<Integer, IngredientDTO> union = new HashMap<>();
        
        for (IngredientDTO ingredient : ingredients) {
            Integer id = ingredient.id();

            if (union.containsKey(id)) {
                IngredientDTO copy = union.get(id);
                double copyAmount = ingredientService.convert(
                    copy.name(), copy.amount(), 
                    ingredientService.parseUnit(copy.unit() == null ? "Count" : copy.unit()),
                    ingredientService.parseUnit(ingredient.unit() == null ? "Count" : ingredient.unit())
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

        union.values().stream().toList().forEach(System.out::println);
    }
}
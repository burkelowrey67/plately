package com.flatironstudios.plately.shoppinglist;

import java.util.List;

import com.flatironstudios.plately.ingredient.IngredientDTO;

public record ShoppingListResponseDTO(List<IngredientDTO> items) {}

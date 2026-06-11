package com.flatironstudios.plately.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController             
@RequestMapping("/api/recipes") 
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/household/{householdId}")
    public ResponseEntity<Map<String, List<RecipeGroupDTO>>> generateHousehold(
        @PathVariable UUID householdId, 
        @AuthenticationPrincipal UUID userId,
        @RequestBody HouseholdRecipeRequestDTO request
    ) {
        return ResponseEntity.ok(
            Map.of(
                "Groups", 
                recipeService.generateRecipes(householdId, userId, request.dietStrategy(), request.number()
            )));
    }

    @GetMapping("/household/{householdId}/member/{memberId}")
    public ResponseEntity<SpoonacularSearchResponseDTO> generateMember(
        @PathVariable UUID householdId, 
        @PathVariable UUID memberId,
        @AuthenticationPrincipal UUID userId
    ) {
        return ResponseEntity.ok(recipeService.generateRecipes(householdId, memberId, userId, 10));
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<SpoonacularRecipeDetailsDTO> findRecipe(@PathVariable int recipeId) {
        return ResponseEntity.ok(recipeService.findRecipe(recipeId));
    }
}
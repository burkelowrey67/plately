package com.flatironstudios.plately.ingredient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record IngredientDTO(int id, String name, Double amount, String unit) {}

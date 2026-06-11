package com.flatironstudios.plately.ingredient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NutrientDTO(String name, double amount, String unit) {}

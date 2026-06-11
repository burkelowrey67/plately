package com.flatironstudios.plately.recipe;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NutritionDTO(
    List<NutrientDTO> nutrients
) {}

package com.flatironstudios.plately.recipe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SpoonacularRecipeSummaryDTO(
    int id, String title, String image
) {}

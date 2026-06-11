package com.flatironstudios.plately.recipe;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;       

@JsonIgnoreProperties(ignoreUnknown = true)
public record SpoonacularSearchResponseDTO(
    Set<SpoonacularRecipeSummaryDTO> results, 
    int offset, int number, int totalResults
) {}

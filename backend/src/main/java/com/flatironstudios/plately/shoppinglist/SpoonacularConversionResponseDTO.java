package com.flatironstudios.plately.shoppinglist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SpoonacularConversionResponseDTO(
    double sourceAmount, String sourceUnit, 
    double targetAmount, String targetUnit, 
    String answer
) {}

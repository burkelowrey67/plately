package com.flatironstudios.plately.ingredient;

import java.security.InvalidParameterException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.flatironstudios.plately.shoppinglist.SpoonacularConversionResponseDTO;

@Service
class ConversionService {
    
    @Value("${spoonacular.api-key}")
    private String apiKey;

    @Value("${spoonacular.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

     public double convert(String name, double amount, IngredientUnit fromUnit, IngredientUnit toUnit) {
        if (fromUnit == null || toUnit == null) throw new InvalidParameterException("Cannot convert ingredient because unit is null");

        if (fromUnit == toUnit) return amount;

        if (fromUnit.type != toUnit.type) {
            String url = UriComponentsBuilder
                .fromUriString(baseUrl + "/recipes/convert")
                .queryParam("apiKey", apiKey)
                .queryParam("ingredientName", name)
                .queryParam("sourceAmount", amount)
                .queryParam("sourceUnit", fromUnit)
                .queryParam("targetUnit", toUnit)
                .toUriString();
            try {
                return restTemplate.getForObject(url, SpoonacularConversionResponseDTO.class).targetAmount();
            }
            catch (Exception exception) {
                throw new RuntimeException("Could not make ingredient conversion");
            }
        }

        return (fromUnit.toBaseUnit * amount) / toUnit.toBaseUnit;
    }
}   

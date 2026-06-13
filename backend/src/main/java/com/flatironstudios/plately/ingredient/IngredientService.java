package com.flatironstudios.plately.ingredient;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class IngredientService {

    @Value("${spoonacular.api-key}")
    private String apiKey;

    @Value("${spoonacular.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private ConversionService conversionService;

    public double convert(String name, double amount, String fromUnit, String toUnit) {
        IngredientUnit fromIngredientUnit = IngredientUnitParser.parse(fromUnit);
        IngredientUnit toIngredientUnit = IngredientUnitParser.parse(toUnit);
        if (fromIngredientUnit == null || toIngredientUnit == null) throw new RuntimeException("Could not parse ingredients");
        return conversionService.convert(name, amount, fromIngredientUnit, toIngredientUnit);
    }
    
    public double convert(String name, double amount, IngredientUnit fromUnit, IngredientUnit toUnit) {
        return conversionService.convert(name, amount, fromUnit, toUnit);
    }

    public IngredientUnit parseUnit(String unit) {
        return IngredientUnitParser.parse(unit);
    }
   

    public SpoonacularIngredientResponseDTO getIngredient(int id) {
        String url = UriComponentsBuilder
            .fromUriString(baseUrl + "/food/ingredients/" + id + "/information")
            .queryParam("apiKey", apiKey)
            .toUriString();

        try {
            return restTemplate
                .getForObject(url, SpoonacularIngredientResponseDTO.class);
        }

        catch(Exception exception) {
            throw new NoSuchElementException("Ingredient not found");
        }
    }
}

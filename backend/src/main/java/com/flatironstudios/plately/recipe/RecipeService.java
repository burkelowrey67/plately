package com.flatironstudios.plately.recipe;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.flatironstudios.plately.household.HouseholdService;
import com.flatironstudios.plately.member.Allergen;
import com.flatironstudios.plately.member.DietType;
import com.flatironstudios.plately.member.Member;

@Service
public class RecipeService {

    @Value("${spoonacular.api-key}")
    private String apiKey;

    @Value("${spoonacular.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private HouseholdService householdService;

    @Autowired UnionService unionService;

    public SpoonacularSearchResponseDTO generateRecipes(UUID householdId, UUID memberId, UUID userId, int number) {
        Member member = householdService.getMember(householdId, memberId, userId);
        return generateRecipes(member.getDietType(), member.getAllergies(), number);
    }

    public SpoonacularSearchResponseDTO generateRecipes(DietType dietType, Set<Allergen> allergies, int number) {
        if (number < 1) throw new InvalidParameterException("Cannot return " + number + " number of recipes");
        if (dietType == null) dietType = DietType.NONE;
        if (allergies == null) allergies = new HashSet<>();

        String url = UriComponentsBuilder
            .fromUriString(baseUrl + "/recipes/complexSearch")
            .queryParam("apiKey", apiKey)
            .queryParam("diet", dietType.name().toLowerCase())
            .queryParam("intolerances", allergies
                .stream()
                .map(allergen -> allergen.name().toLowerCase())
                .collect(Collectors.joining(","))
            )
            .queryParam("number", number)
            .queryParam("addRecipeNutrition", false)
            .toUriString();


        SpoonacularSearchResponseDTO response = restTemplate.getForObject(url, SpoonacularSearchResponseDTO.class);

        if (response.results().size() == 0) throw new NoSuchElementException("Recipes not found");
        return response;
    }

    public List<RecipeGroupDTO> generateRecipes(UUID householdId, UUID userId, DietStrategy dietStrategy, int number) {
        List<RecipeGroupDTO> responses = new ArrayList<>();
        List<Member> members = householdService.getMembers(householdId, userId);

        if (dietStrategy == DietStrategy.UNION) {
            Set<Allergen> allergies = unionService.allergenUnion(members);
            Set<DietType> diets = unionService.dietTypeUnion(members);

            responses = diets.parallelStream()
                .map(diet -> new RecipeGroupDTO(
                    members.stream()
                        .filter(member -> member.getDietType() == diet)
                        .map(Member::getId)
                        .toList(),
                    generateRecipes(diet, allergies, number)
                ))
                .toList();
        }

        else if (dietStrategy == DietStrategy.PER_MEMBER) {
            responses = members.parallelStream()
                .map(member -> new RecipeGroupDTO(
                    List.of(member.getId()),
                    generateRecipes(
                        member.getDietType(),
                        member.getAllergies(),
                        number
                    )
                ))
                .toList();
        }

        return responses;
    }


    public SpoonacularRecipeDetailsDTO findRecipe(int recipeId) {
        String url = UriComponentsBuilder
            .fromUriString(baseUrl + "/recipes/" + recipeId + "/information")
            .queryParam("apiKey", apiKey)
            .queryParam("includeNutrition", true)
            .toUriString();

        SpoonacularRecipeDetailsDTO result = restTemplate.getForObject(url, SpoonacularRecipeDetailsDTO.class);
        if (result == null) throw new NoSuchElementException("Recipe not found");
        return result;
    }
}

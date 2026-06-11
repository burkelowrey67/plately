package com.flatironstudios.plately.recipe;

import java.util.List;
import java.util.UUID;

public record RecipeGroupDTO(List<UUID> memberIds, SpoonacularSearchResponseDTO recipes) {}

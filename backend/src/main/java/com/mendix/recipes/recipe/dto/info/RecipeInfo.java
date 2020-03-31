package com.mendix.recipes.recipe.dto.info;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface RecipeInfo {

    UUID getUuid();

    String getTitle();

    Integer getYield();

    List<DivisionInfo> getIngredients();

    List<DirectionStepInfo> getDirections();

    Set<CategoryInfo> getCategories();
}

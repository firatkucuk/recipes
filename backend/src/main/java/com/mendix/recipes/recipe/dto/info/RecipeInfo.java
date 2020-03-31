package com.mendix.recipes.recipe.dto.info;

import java.util.List;
import java.util.Set;

public interface RecipeInfo {

    String getUuid();

    String getTitle();

    Integer getYield();

    List<DivisionInfo> getIngredients();

    List<DirectionStepInfo> getDirections();

    Set<CategoryInfo> getCategories();
}

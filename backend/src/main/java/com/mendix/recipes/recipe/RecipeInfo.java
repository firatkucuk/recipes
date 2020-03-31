package com.mendix.recipes.recipe;

import java.util.Set;

public interface RecipeInfo {

    String getUuid();

    String getTitle();

    int getYield();

    Set<DivisionInfo> getIngredients();

    Set<DirectionStepInfo> getDirections();

    Set<CategoryInfo> getCategories();
}

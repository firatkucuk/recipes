package com.mendix.recipes.recipe;

import java.util.List;
import java.util.Set;

public interface RecipeInfo {

    String getUuid();

    String getTitle();

    int getYield();

    List<DivisionInfo> getIngredients();

    List<DirectionStepInfo> getDirections();

    Set<CategoryInfo> getCategories();
}

package com.mendix.recipes.recipe.dto.info;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface RecipeInfo<I extends DivisionInfo<?>, D extends DirectionStepInfo, C extends CategoryInfo> {

    UUID getUuid();

    String getTitle();

    Integer getYield();

    List<I> getIngredients();

    List<D> getDirections();

    Set<C> getCategories();
}

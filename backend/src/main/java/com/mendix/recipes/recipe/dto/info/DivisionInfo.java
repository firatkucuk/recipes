package com.mendix.recipes.recipe.dto.info;

import java.util.List;

public interface DivisionInfo<I extends IngredientItemInfo> {

    String getTitle();

    List<I> getItems();
}

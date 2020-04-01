package com.mendix.recipes.recipe;

import com.mendix.recipes.recipe.dto.info.CategoryInfo;
import com.mendix.recipes.recipe.dto.info.DirectionStepInfo;
import com.mendix.recipes.recipe.dto.info.DivisionInfo;
import com.mendix.recipes.recipe.dto.info.IngredientItemInfo;
import com.mendix.recipes.recipe.dto.info.RecipeInfo;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.Data;

@Data
class RecipeInfoImpl implements RecipeInfo<DivisionInfoImpl, DirectionStepInfoImpl, CategoryInfoImpl> {

    private UUID                        uuid;
    private String                      title;
    private Integer                     yield;
    private List<DivisionInfoImpl>      ingredients;
    private List<DirectionStepInfoImpl> directions;
    private Set<CategoryInfoImpl>       categories;
}

@Data
class DivisionInfoImpl implements DivisionInfo<IngredientItemInfoImpl> {

    private String                       title;
    private List<IngredientItemInfoImpl> items;
}

@Data
class DirectionStepInfoImpl implements DirectionStepInfo {

    private String content;
}

@Data
class CategoryInfoImpl implements CategoryInfo {

    private String name;
}

@Data
class IngredientItemInfoImpl implements IngredientItemInfo {

    private String quantity;
    private String unit;
    private String content;
}

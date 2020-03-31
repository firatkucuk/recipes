package com.mendix.recipes.recipe.dto.form;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IngredientForm {

    private String quantity;
    private String unit;
    @NotNull
    private String content;
}

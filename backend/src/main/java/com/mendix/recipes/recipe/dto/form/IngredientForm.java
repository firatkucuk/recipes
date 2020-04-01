package com.mendix.recipes.recipe.dto.form;

import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class IngredientForm {

    @Length(max = 15)
    private String quantity;
    @Length(max = 50)
    private String unit;
    @NotNull
    @Length(max = 200)
    private String content;
}

package com.mendix.recipes.recipe.dto.form;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class IngredientDivisionForm {

    private String               title;
    private List<IngredientForm> items = new ArrayList<>();
}

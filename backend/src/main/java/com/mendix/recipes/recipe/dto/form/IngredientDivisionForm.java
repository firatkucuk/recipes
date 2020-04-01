package com.mendix.recipes.recipe.dto.form;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class IngredientDivisionForm {

    @Length(max = 100)
    private String               title;
    @Valid
    private List<IngredientForm> items = new ArrayList<>();
}

package com.mendix.recipes.recipe.dto.form;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RecipeForm {

    @NotNull
    @Length(max = 150)
    private String title;

    @NotNull
    @Min(0)
    private Integer yield;

    private List<String> directions = new ArrayList<>();

    @Valid
    private List<IngredientDivisionForm> ingredients = new ArrayList<>();

    private List<UUID> categories = new ArrayList<>();
}

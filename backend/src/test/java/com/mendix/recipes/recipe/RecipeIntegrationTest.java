package com.mendix.recipes.recipe;

import com.mendix.recipes.common.ResponseType;
import com.mendix.recipes.recipe.dto.form.IngredientDivisionForm;
import com.mendix.recipes.recipe.dto.form.IngredientForm;
import com.mendix.recipes.recipe.dto.form.RecipeForm;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RecipeIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void addRecipe() {

        final IngredientForm ingredient = new IngredientForm();
        ingredient.setQuantity("1/2");
        ingredient.setUnit("cup");
        ingredient.setContent("Water");

        final IngredientDivisionForm divisionForm = new IngredientDivisionForm();
        divisionForm.setTitle("MAIN");
        divisionForm.setItems(List.of(ingredient));

        final RecipeForm recipeForm = new RecipeForm();
        recipeForm.setTitle("New Recipe");
        recipeForm.setYield(4);
        recipeForm.setCategories(
            List.of(
                UUID.fromString("2f44e5ec-7375-4bff-9409-698c536c84ba"),
                UUID.fromString("ee4ab6ec-e023-4085-98d1-b1c6cb84ba4a"),
                UUID.randomUUID()
            )
        );
        recipeForm.setDirections(List.of("some direction", "some other direction"));
        recipeForm.setIngredients(List.of(divisionForm));

        final var response = restTemplate.postForEntity("/api/recipe", recipeForm, RecipeAddRestResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMsgId()).isEqualTo("recipeAdded");
        assertThat(response.getBody().getType()).isEqualTo(ResponseType.INFO);
        assertThat(response.getBody().getErrorCode()).isNull();
        assertThat(response.getBody().getText()).isNotEmpty();
        assertThat(response.getBody().getText()).isNotEqualTo("recipeAdded");

        final RecipeInfoImpl data = response.getBody().getData();
        assertThat(data.getTitle()).isEqualTo("New Recipe");
        assertThat(data.getYield()).isEqualTo(4);

        final Set<CategoryInfoImpl> categories = data.getCategories();
        assertThat(categories).isNotNull();
        assertThat(categories).hasSize(2);

        final Iterator<CategoryInfoImpl> iterator = categories.iterator();
        assertThat(iterator.next().getName()).isEqualTo("Main dish");
        assertThat(iterator.next().getName()).isEqualTo("Microwave");

        final List<DirectionStepInfoImpl> directions = data.getDirections();
        assertThat(directions).isNotNull();
        assertThat(directions).hasSize(2);
        assertThat(directions.get(0).getContent()).isEqualTo("some direction");
        assertThat(directions.get(1).getContent()).isEqualTo("some other direction");

        final List<DivisionInfoImpl> divisions = data.getIngredients();
        assertThat(divisions).isNotNull();
        assertThat(divisions).hasSize(1);


    }
}

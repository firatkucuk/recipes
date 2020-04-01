package com.mendix.recipes.recipe;

import com.mendix.recipes.common.exception.ItemNotFoundException;
import com.mendix.recipes.domain.Recipe;
import com.mendix.recipes.recipe.dto.form.IngredientDivisionForm;
import com.mendix.recipes.recipe.dto.form.IngredientForm;
import com.mendix.recipes.recipe.dto.form.RecipeForm;
import com.mendix.recipes.recipe.dto.info.CategoryInfo;
import com.mendix.recipes.recipe.dto.info.DirectionStepInfo;
import com.mendix.recipes.recipe.dto.info.DivisionInfo;
import com.mendix.recipes.recipe.dto.info.RecipeInfo;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @Mock
    private DirectionStepRepository      directionStepRepository;
    @Mock
    private IngredientDivisionRepository ingredientDivisionRepository;
    @Mock
    private IngredientItemRepository     ingredientItemRepository;
    @Mock
    private RecipeCategoryRepository     categoryRepository;
    @Mock
    private RecipeRepository             recipeRepository;
    private RecipeService                recipeService;

    @BeforeEach
    void setUp() {

        recipeService = new RecipeService(
            directionStepRepository,
            ingredientDivisionRepository,
            ingredientItemRepository,
            categoryRepository,
            recipeRepository
        );
    }

    @Test
    void add_succeeds() {

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
        recipeForm.setCategories(List.of(UUID.fromString("2f44e5ec-7375-4bff-9409-698c536c84ba")));
        recipeForm.setDirections(List.of("some direction", "some other direction"));
        recipeForm.setIngredients(List.of(divisionForm));

        recipeService.add(recipeForm);

        Mockito.verify(recipeRepository, Mockito.times(2)).save(Mockito.any(Recipe.class));
        Mockito.verify(categoryRepository, Mockito.times(1)).findMatchedCategories(Mockito.any());
        Mockito.verify(directionStepRepository, Mockito.times(2)).save(Mockito.any());
        Mockito.verify(ingredientDivisionRepository, Mockito.times(2)).save(Mockito.any());
        Mockito.verify(ingredientItemRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void get_notFound() {

        Mockito.when(recipeRepository.findByUuid(Mockito.anyString()))
            .thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> recipeService.get(UUID.randomUUID()));
    }

    @Test
    void get_succeeds() {

        final UUID uuid = UUID.randomUUID();

        Mockito.when(recipeRepository.findByUuid(Mockito.anyString()))
            .thenReturn(Optional.of(new RecipeInfo() {
                @Override
                public UUID getUuid() {
                    return uuid;
                }

                @Override
                public String getTitle() {
                    return null;
                }

                @Override
                public Integer getYield() {
                    return null;
                }

                @Override
                public List<DivisionInfo> getIngredients() {
                    return null;
                }

                @Override
                public List<DirectionStepInfo> getDirections() {
                    return null;
                }

                @Override
                public Set<CategoryInfo> getCategories() {
                    return null;
                }
            }));

        final RecipeInfo info = recipeService.get(uuid);

        assertThat(info).isNotNull();
        assertThat(info.getUuid()).isEqualTo(uuid);
    }
}

package com.mendix.recipes.recipe;

import com.mendix.recipes.common.exception.ItemNotFoundException;
import com.mendix.recipes.domain.Category;
import com.mendix.recipes.domain.Recipe;
import com.mendix.recipes.recipe.dto.form.IngredientDivisionForm;
import com.mendix.recipes.recipe.dto.form.IngredientForm;
import com.mendix.recipes.recipe.dto.form.RecipeForm;
import com.mendix.recipes.recipe.dto.info.RecipeInfo;
import java.util.Collections;
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

        final RecipeInfoImpl recipeInfo = new RecipeInfoImpl();
        recipeInfo.setUuid(uuid);

        Mockito.when(recipeRepository.findByUuid(Mockito.anyString()))
            .thenReturn(Optional.of(recipeInfo));

        final var info = recipeService.get(uuid);

        assertThat(info).isNotNull();
        assertThat(info.getUuid()).isEqualTo(uuid);
    }

    @Test
    void list_nullCategoryListAndNullTerm() {

        recipeService.list(null, null);
        Mockito.verify(categoryRepository, Mockito.never()).findMatchedCategories(Mockito.any());
        Mockito.verify(recipeRepository, Mockito.times(1)).listAll();

        recipeService.list(null, "");
        Mockito.verify(categoryRepository, Mockito.never()).findMatchedCategories(Mockito.any());
        Mockito.verify(recipeRepository, Mockito.times(2)).listAll();

        recipeService.list(Collections.emptyList(), null);
        Mockito.verify(categoryRepository, Mockito.never()).findMatchedCategories(Mockito.any());
        Mockito.verify(recipeRepository, Mockito.times(3)).listAll();

        recipeService.list(Collections.emptyList(), "");
        Mockito.verify(categoryRepository, Mockito.never()).findMatchedCategories(Mockito.any());
        Mockito.verify(recipeRepository, Mockito.times(4)).listAll();
    }

    @Test
    void list_unMatchedCategoriesAndEmptyTerm() {

        Mockito.when(categoryRepository.findMatchedCategories(Mockito.anyList()))
            .thenReturn(Collections.emptySet());

        recipeService.list(List.of(UUID.randomUUID()), "");
        Mockito.verify(recipeRepository, Mockito.times(1)).listAll();
    }

    @Test
    void list_unMatchedCategoriesAndTerm() {

        Mockito.when(categoryRepository.findMatchedCategories(Mockito.anyList()))
            .thenReturn(Collections.emptySet());

        recipeService.list(List.of(UUID.randomUUID()), "term");
        Mockito.verify(recipeRepository, Mockito.times(1)).filterByTerm(Mockito.anyString());
    }

    @Test
    void list_matchedCategoriesAndEmptyTerm() {

        final UUID uuid = UUID.randomUUID();

        Mockito.when(categoryRepository.findMatchedCategories(Mockito.anyList()))
            .thenReturn(Set.of(Category.builder().uuid(uuid.toString()).build()));

        recipeService.list(List.of(uuid), "");
        Mockito.verify(recipeRepository, Mockito.times(1)).filterByCategories(Mockito.anyList());
    }

    @Test
    void list_matchedCategoriesAndSomeTerm() {

        final UUID uuid = UUID.randomUUID();

        Mockito.when(categoryRepository.findMatchedCategories(Mockito.anyList()))
            .thenReturn(Set.of(Category.builder().uuid(uuid.toString()).build()));

        recipeService.list(List.of(uuid), "term");
        Mockito.verify(recipeRepository, Mockito.times(1))
            .filterByTermAndCategories(Mockito.anyString(), Mockito.anyList());
    }
}

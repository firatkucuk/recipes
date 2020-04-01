package com.mendix.recipes.recipe;

import com.mendix.recipes.common.exception.ItemNotFoundException;
import com.mendix.recipes.domain.DirectionStep;
import com.mendix.recipes.domain.IngredientDivision;
import com.mendix.recipes.domain.IngredientItem;
import com.mendix.recipes.domain.Recipe;
import com.mendix.recipes.recipe.dto.form.IngredientDivisionForm;
import com.mendix.recipes.recipe.dto.form.IngredientForm;
import com.mendix.recipes.recipe.dto.form.RecipeForm;
import com.mendix.recipes.recipe.dto.info.RecipeInfo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class RecipeService {

    private final DirectionStepRepository      directionStepRepository;
    private final IngredientDivisionRepository ingredientDivisionRepository;
    private final IngredientItemRepository     ingredientItemRepository;
    private final RecipeCategoryRepository     categoryRepository;
    private final RecipeRepository             recipeRepository;

    @NonNull
    UUID add(@NonNull final RecipeForm form) {

        final List<String> categories = form.getCategories().stream()
            .map((UUID::toString))
            .collect(Collectors.toList());

        final UUID uuid = UUID.randomUUID();

        final Recipe recipe = new Recipe();
        recipe.setUuid(uuid.toString());
        recipe.setTitle(form.getTitle());
        recipe.setYield(form.getYield());
        recipe.setCreatedAt(LocalDateTime.now());

        categoryRepository.findMatchedCategories(categories).forEach(c -> {
            c.getRecipes().add(recipe);
            recipe.getCategories().add(c);
        });

        recipeRepository.save(recipe);

        createDirections(recipe, form.getDirections());
        createIngredients(recipe, form.getIngredients());

        recipeRepository.save(recipe);

        return uuid;
    }

    @NonNull
    RecipeInfo<?, ?, ?> get(@NonNull final UUID uuid) {

        final Optional<RecipeInfo<?, ?, ?>> recipeInfoOptional = recipeRepository.findByUuid(uuid.toString());

        if (recipeInfoOptional.isEmpty()) {
            throw new ItemNotFoundException("recipeNotFound");
        }

        return recipeInfoOptional.get();
    }

    @NonNull
    List<RecipeInfo<?, ?, ?>> list(@Nullable List<String> categoryList, @Nullable final String term) {

        if (categoryList == null) {
            categoryList = new ArrayList<>();
        }

        return recipeRepository.list(categoryList.size(), categoryList, term);
    }

    private void createDirections(final Recipe recipe, final List<String> directionContents) {

        for (final String directionContent : directionContents) {
            final DirectionStep direction = new DirectionStep();
            direction.setUuid(UUID.randomUUID().toString());
            direction.setRecipe(recipe);
            direction.setContent(directionContent);
            direction.setCreatedAt(LocalDateTime.now());

            directionStepRepository.save(direction);
            recipe.getDirections().add(direction);
        }
    }

    private void createIngredientItems(final IngredientDivision division, final List<IngredientForm> formIngredients) {

        for (final IngredientForm formIngredient : formIngredients) {
            final IngredientItem ingredientItem = new IngredientItem();
            ingredientItem.setUuid(UUID.randomUUID().toString());
            ingredientItem.setDivision(division);
            ingredientItem.setQuantity(formIngredient.getQuantity());
            ingredientItem.setUnit(formIngredient.getUnit());
            ingredientItem.setContent(formIngredient.getContent());
            ingredientItem.setCreatedAt(LocalDateTime.now());
            ingredientItemRepository.save(ingredientItem);
            division.getItems().add(ingredientItem);
        }
    }

    private void createIngredients(final Recipe recipe, final List<IngredientDivisionForm> formDivisions) {

        for (final IngredientDivisionForm formDivision : formDivisions) {
            final IngredientDivision division = new IngredientDivision();
            division.setUuid(UUID.randomUUID().toString());
            division.setRecipe(recipe);
            division.setTitle(formDivision.getTitle());
            division.setCreatedAt(LocalDateTime.now());
            ingredientDivisionRepository.save(division);

            createIngredientItems(division, formDivision.getItems());

            ingredientDivisionRepository.save(division);
            recipe.getIngredients().add(division);
        }
    }
}

package com.mendix.recipes.recipe;

import com.mendix.recipes.common.exception.ItemNotFoundException;
import com.mendix.recipes.domain.Category;
import com.mendix.recipes.domain.DirectionStep;
import com.mendix.recipes.domain.IngredientDivision;
import com.mendix.recipes.domain.IngredientItem;
import com.mendix.recipes.domain.Recipe;
import com.mendix.recipes.recipe.dto.form.IngredientDivisionForm;
import com.mendix.recipes.recipe.dto.form.IngredientForm;
import com.mendix.recipes.recipe.dto.form.RecipeForm;
import com.mendix.recipes.recipe.dto.info.RecipeInfo;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
    <T extends RecipeInfo<?, ?, ?>> T get(@NonNull final UUID uuid) {

        final var recipeInfoOptional = recipeRepository.<T>findByUuid(uuid.toString());

        if (recipeInfoOptional.isEmpty()) {
            throw new ItemNotFoundException("recipeNotFound");
        }

        return recipeInfoOptional.get();
    }

    @NonNull
    <T extends RecipeInfo<?, ?, ?>> List<T> list(
        @Nullable final List<UUID> categoryUuids, @Nullable final String term
    ) {

        final List<String> categoryList        = findTheMatchedCategoryUuids(categoryUuids);
        final boolean      noCategoryFiltering = categoryList.isEmpty();
        final boolean      noTermFiltering     = StringUtils.isEmpty(term);

        if (noCategoryFiltering && noTermFiltering) {
            return recipeRepository.listAll();
        } else if (noCategoryFiltering) {
            return recipeRepository.filterByTerm(term);
        } else if (noTermFiltering) {
            return recipeRepository.filterByCategories(categoryList);
        }

        return recipeRepository.filterByTermAndCategories(term, categoryList);
    }

    private void createDirections(@NonNull final Recipe recipe, @NonNull final List<String> directionContents) {

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

    private void createIngredientItems(
        @NonNull final IngredientDivision division, @NonNull final List<IngredientForm> formIngredients
    ) {

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

    private void createIngredients(
        @NonNull final Recipe recipe, @NonNull final List<IngredientDivisionForm> formDivisions
    ) {

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

    @NonNull
    private List<String> findTheMatchedCategoryUuids(@Nullable final List<UUID> categoryUuids) {

        if (CollectionUtils.isEmpty(categoryUuids)) {
            return Collections.emptyList();
        }

        final var uuids = categoryUuids.stream().map(UUID::toString).collect(Collectors.toList());

        return categoryRepository.findMatchedCategories(uuids).stream()
            .map(Category::getUuid)
            .collect(Collectors.toList());
    }
}

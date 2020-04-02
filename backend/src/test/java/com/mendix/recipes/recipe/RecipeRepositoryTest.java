package com.mendix.recipes.recipe;

import com.mendix.recipes.recipe.dto.info.RecipeInfo;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    void filterByCategories_emptyList() {

        final List<RecipeInfo<?, ?, ?>> recipes = recipeRepository.filterByCategories(Collections.emptyList());
        assertThat(recipes).hasSize(0);
    }

    @Test
    void filterByCategories_unMatched() {

        final List<RecipeInfo<?, ?, ?>> recipes = recipeRepository.filterByCategories(List.of(UUID.randomUUID().toString()));
        assertThat(recipes).hasSize(0);
    }

    @Test
    void filterByCategories_matched() {

        final List<RecipeInfo<?, ?, ?>> recipes = recipeRepository.filterByCategories(List.of("2f44e5ec-7375-4bff-9409-698c536c84ba"));
        assertThat(recipes).hasSize(1);
        assertThat(recipes.get(0).getTitle()).isNotEmpty();
    }

    @Test
    void filterByCategories_duplicateMatched() {

        final List<RecipeInfo<?, ?, ?>> recipes = recipeRepository.filterByCategories(
            List.of("2f44e5ec-7375-4bff-9409-698c536c84ba", "2f44e5ec-7375-4bff-9409-698c536c84ba")
        );
        assertThat(recipes).hasSize(1);
        assertThat(recipes.get(0).getTitle()).isNotEmpty();
    }

    @Test
    void filterByCategories_MatchesOfSameRecipes() {

        final List<RecipeInfo<?, ?, ?>> recipes = recipeRepository.filterByCategories(
            List.of("2f44e5ec-7375-4bff-9409-698c536c84ba", "6eb1626d-2bcc-42a5-a1aa-befc1e47ab59")
        );
        assertThat(recipes).hasSize(1);
        assertThat(recipes.get(0).getTitle()).isNotEmpty();
    }

    @Test
    void filterByCategories_MatchesOfDifferentRecipes() {

        final List<RecipeInfo<?, ?, ?>> recipes = recipeRepository.filterByCategories(
            List.of("2f44e5ec-7375-4bff-9409-698c536c84ba", "739ef371-cdef-4631-a893-0b1e1fd20512")
        );
        assertThat(recipes).hasSize(2);
        assertThat(recipes.get(0).getTitle()).isNotEmpty();
    }

    @Test
    void filterByTerm_unMatchedTerm() {

        final List<RecipeInfo<?, ?, ?>> recipes = recipeRepository.filterByTerm("xxxxxxxxxxxxxxx");
        assertThat(recipes).hasSize(0);
    }

    @Test
    void filterByTerm_matched() {

        final List<RecipeInfo<?, ?, ?>> recipes = recipeRepository.filterByTerm("MasterCook");
        assertThat(recipes).hasSize(1);
        assertThat(recipes.get(0).getTitle()).isNotEmpty();
    }

    @Test
    void filterByTerm_multipleMatchedInSameRecipe() {

        final List<RecipeInfo<?, ?, ?>> recipes = recipeRepository.filterByTerm("Chili");
        assertThat(recipes).hasSize(1);
        assertThat(recipes.get(0).getTitle()).isNotEmpty();
    }

    @Test
    void filterByTerm_multipleMatchedInDifferentRecipes() {

        final List<RecipeInfo<?, ?, ?>> recipes = recipeRepository.filterByTerm("butter");
        assertThat(recipes).hasSize(3);
        assertThat(recipes.get(0).getTitle()).isNotEmpty();
    }

    @Test
    void filterByTermAndCategories_unMatchedTermAndUnMatchedCategories() {

        final List<RecipeInfo<?, ?, ?>> recipes = recipeRepository.filterByTermAndCategories(
            "xxxxxxxxxxxxxxx", List.of(UUID.randomUUID().toString())
        );
        assertThat(recipes).isEmpty();
    }

    @Test
    void filterByTermAndCategories_matchedTermAndUnMatchedCategories() {

        final List<RecipeInfo<?, ?, ?>> recipes = recipeRepository.filterByTermAndCategories(
            "MasterCook", List.of(UUID.randomUUID().toString())
        );
        assertThat(recipes).isEmpty();
    }

    @Test
    void filterByTermAndCategories_unMatchedTermAndMatchedCategories() {

        final List<RecipeInfo<?, ?, ?>> recipes = recipeRepository.filterByTermAndCategories(
            "xxxxxxxxxxxxxxx", List.of("2f44e5ec-7375-4bff-9409-698c536c84ba")
        );
        assertThat(recipes).isEmpty();
    }

    @Test
    void filterByTermAndCategories_matchedTermAndMatchedCategoriesInSameRecipe() {

        final List<RecipeInfo<?, ?, ?>> recipes = recipeRepository.filterByTermAndCategories(
            "MasterCook", List.of("2f44e5ec-7375-4bff-9409-698c536c84ba")
        );
        assertThat(recipes).hasSize(1);
        assertThat(recipes.get(0).getTitle()).isNotEmpty();
    }

    @Test
    void filterByTermAndCategories_matchedTermAndMatchedCategoriesInDifferentRecipes() {

        final List<RecipeInfo<?, ?, ?>> recipes = recipeRepository.filterByTermAndCategories(
            "MasterCook", List.of("6d6da1d2-0f00-424e-9828-fcce3904f100")
        );
        assertThat(recipes).hasSize(0);
    }

    @Test
    void findByUuid_notFound() {

        final var optionalRecipeInfo = recipeRepository.findByUuid(UUID.randomUUID().toString());
        assertThat(optionalRecipeInfo).isEmpty();
    }

    @Test
    void findByUuid_itemFound() {

        final var optionalRecipeInfo = recipeRepository.findByUuid("b6baecce-2d60-4652-b75d-df8194d958fe");
        assertThat(optionalRecipeInfo).isNotEmpty();
        assertThat(optionalRecipeInfo.get().getTitle()).isEqualTo("30 Minute Chili");
    }

    @Test
    void listAll() {

        final List<RecipeInfo<?, ?, ?>> recipes = recipeRepository.listAll();
        assertThat(recipes).hasSize(3);
    }
}

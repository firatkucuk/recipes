package com.mendix.recipes.recipe;

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
}

package com.mendix.recipes.recipe;

import com.mendix.recipes.domain.Category;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class RecipeCategoryRepositoryTest {

    @Autowired
    private RecipeCategoryRepository categoryRepository;

    @Test
    void findMatchedCategories_noMatch() {

        final Set<Category> categories = categoryRepository.findMatchedCategories(
            List.of(UUID.randomUUID().toString(), UUID.randomUUID().toString())
        );

        assertThat(categories).isEmpty();
    }

    @Test
    void findMatchedCategories_ignoreUnmatched() {

        final Set<Category> categories = categoryRepository.findMatchedCategories(
            List.of(
                "2f44e5ec-7375-4bff-9409-698c536c84ba",
                UUID.randomUUID().toString(),
                "6d6da1d2-0f00-424e-9828-fcce3904f100"
            )
        );

        assertThat(categories).hasSize(2);
        final Iterator<Category> iterator = categories.iterator();
        assertThat(iterator.next().getName()).isEqualTo("Main dish");
        assertThat(iterator.next().getName()).isEqualTo("Vegetables");
    }

    @Test
    void findMatchedCategories_ignoreDuplicates() {

        final Set<Category> categories = categoryRepository.findMatchedCategories(
            List.of(
                "2f44e5ec-7375-4bff-9409-698c536c84ba",
                "2f44e5ec-7375-4bff-9409-698c536c84ba"
            )
        );

        assertThat(categories).hasSize(1);
        final Iterator<Category> iterator = categories.iterator();
        assertThat(iterator.next().getName()).isEqualTo("Main dish");
    }
}
